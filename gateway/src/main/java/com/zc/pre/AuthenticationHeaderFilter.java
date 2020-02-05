package com.zc.pre;


import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.zc.pojo.User;
import com.zc.util.RedisUtil;
import com.zc.util.TokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

public class AuthenticationHeaderFilter extends ZuulFilter {

    @Value("${permission.key}")
    private String PERMISSION_KEY;
    @Value("${userInfo.key}")
    private String USER_INFO_KYE;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisUtil redisUtil;

    private static Logger log = LoggerFactory.getLogger(AuthenticationHeaderFilter.class);

    private static final String secretKey = "ZC-20200115";

    private static final String[][] preAuthenticationIgnoreUris = {
            {"/public", "*"},

    };

    //	所有request都需要验证Token，除了上面ignore的
    private static final String[][] preAuthenticationMustUris = {
            {"/", "*"}
    };

    //If request method is POST
//	private static final String[][] preAuthenticationMustUris = {
//		{"/user", "POST"}
//	};

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String uri = request.getRequestURI().toString().toLowerCase();
        String method = request.getMethod();

        log.info(String.format("====AuthenticationHeaderFilter.shouldFilter - http method: (%s)", method));
        log.info(String.format("====AuthenticationHeaderFilter.shouldFilter - url", uri));
        for (int i = 0; i < preAuthenticationIgnoreUris.length; i++) {
            if (uri.startsWith(preAuthenticationIgnoreUris[i][0].toLowerCase()) &&
                    (preAuthenticationIgnoreUris[i][1].equals("*") || method.equalsIgnoreCase(preAuthenticationIgnoreUris[i][1]))) {
                log.info("this will be not use filter");
                return false;
            }
        }
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String uri = request.getRequestURI().toString().toLowerCase();
        String method = request.getMethod();

        log.info(String.format("====AuthenticationHeaderFilter.run - %s request to %s", request.getMethod(), uri));

        //不允许外部访问接口
        if (uri.contains("/v1.0/user/rebate/update")) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpServletResponse.SC_OK);
            ctx.setResponseBody("该接口不允许外部访问");
            ctx.set("isSuccess", false);
            return null;
        }

        // 1. clear userInfo from HTTP header to avoid fraud attack
//        ctx.addZuulRequestHeader("user-info", "");

        // 2. verify the passed user token
        String userToken = request.getHeader("token");

//		log.info(String.format("====AuthenticationHeaderFilter.run - UserToken: %s",userToken));
//        Claims claims = null;
        String userInfo = null;
        if (userToken != null && !userToken.trim().equals("null") && !userToken.trim().equals("")) {
            try {
//                log.info("UserToken has value.....");
//                claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(userToken).getBody();
//                log.info("claims is:" + claims);
//                String userId = "";
//                try {
//                    userId = (String) claims.get("userId");
//                    log.info("这里是String类型");
//                } catch (Exception ex) {
//                    userId = ((Integer) claims.get("userId")).toString();
//                    log.info("这里是Integer类型");
//                }
                long userId = TokenUtil.getUserId(userToken);
                log.info("userId is:" + userId);
                String userString = redisUtil.getStr(USER_INFO_KYE + userId);
                if (userString == null) {
                    this.stopZuulRoutingWithError(ctx, HttpStatus.UNAUTHORIZED,
                            "非法请求");
                    return null;
                }
                redisUtil.setStr(USER_INFO_KYE + userId, userString, 30L);
                User user = JSONObject.parseObject(userString, User.class);
                int roleId = user.getUserRoleId();
                String prefixString = (String) stringRedisTemplate.opsForHash().get(PERMISSION_KEY, roleId);
                String prefix = uri.split("/")[1];
                if (!prefixString.contains(prefix)) {
                    this.stopZuulRoutingWithError(ctx, HttpStatus.UNAUTHORIZED,
                            "权限不足");
                    return null;
                }
            } catch (Exception e) {
                this.stopZuulRoutingWithError(ctx, HttpStatus.UNAUTHORIZED,
                        "Expired User Token for the API (" + request.getRequestURI().toString() + ")");
                return null;
            }
        } else {
            this.stopZuulRoutingWithError(ctx, HttpStatus.UNAUTHORIZED,
                    "Expired User Token for the API (" + request.getRequestURI().toString() + ")");
        }

//		log.info(String.format("=====AuthenticationHeaderFilter.run - userInfo: %s", userInfo));
        // 3. set userInfo to HTTP header
//        if (userInfo != null) {
//            String encodeUserInfo = userInfo;
//            try {
//                encodeUserInfo = URLEncoder.encode(userInfo, "UTF-8");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            ctx.addZuulRequestHeader("user-info", encodeUserInfo);
//        }
//		log.info(String.format("====AuthenticationHeaderFilter.run - userInfo: %s", request.getHeader("user-info")));
//		log.info(String.format("====AuthenticationHeaderFilter.run - userInfo: %s", ctx.getZuulRequestHeaders().get
//		("user-info")));

        // 4. stop the filter chain if userInfo is must
		/*if (userInfo == null) {
			for (int i=0; i<preAuthenticationMustUris.length; i++) {
				if (uri.startsWith(preAuthenticationMustUris[i][0].toLowerCase()) && 
						(preAuthenticationMustUris[i][1].equals("*") || method.equalsIgnoreCase
						(preAuthenticationMustUris[i][1])) ) {
					log.info(String.format("userInfo is missed for %s", uri));
					
					this.stopZuulRoutingWithError(ctx, HttpStatus.UNAUTHORIZED, "User Login is needed for the API (" +
					 request.getRequestURI().toString() + ")");
					
					return null;
				}
			}
		}*/

        return null;
    }

    private void stopZuulRoutingWithError(RequestContext ctx, HttpStatus status, String responseText) {
        ctx.removeRouteHost();
        ctx.setResponseStatusCode(status.value());
        ctx.setResponseBody(responseText);
        ctx.setSendZuulResponse(false);
    }
}
