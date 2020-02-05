package com.zc.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Token类
 */
public class TokenUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(TokenUtil.class);

	/**
	 * 颁发Token
	 * @param userId
	 * @param phone
	 * @return
	 */
	public  static String createToken(long userId,  String phone){
		String userToken = Jwts.builder()
				.setSubject("smart-comminity")
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24*600))
				.claim("userId", userId)
				.claim("phone",phone)
		.signWith(SignatureAlgorithm.HS512, CommonConstants.SECRETKEY).compact();
		
		return userToken;
	}

	/**
	 * 获取用户id
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public  static long getUserId(String token) throws Exception {
		Claims claims = Jwts.parser().setSigningKey(CommonConstants.SECRETKEY).parseClaimsJws(token).getBody();
		Map map  = new HashMap();
		long userId = (Integer)claims.get("userId");
	    return userId;
	}

	/**
	 *  获取用户电话
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public  static String getUserPhone(String token) throws Exception {
		
		Claims claims = Jwts.parser().setSigningKey(CommonConstants.SECRETKEY).parseClaimsJws(token).getBody();
		Map map  = new HashMap();
		String phone = (String)claims.get("phone");
	    return phone;
	}


	/**
	 * 验证Token
	 * @param token
	 * @return
	 */
	public static Object validToken(String token) throws Exception {
		Map map = new HashMap();
		long userId;
		try {
			userId = TokenUtil.getUserId(token);
		} catch (Exception e) {
			map.put(CommonConstants.RESP_CODE,CommonConstants.ERROR_TOKEN);
			map.put(CommonConstants.RESP_MESSAGE, "token无效");
			return map;		
		}
		return userId;
	
	}
	
	
	
}
