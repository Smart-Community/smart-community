package com.zc.client;

import com.zc.client.hystrix.UserHystrix;
import com.zc.pojo.User;
import com.zc.vo.LayuiVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-04-21:23
 */
@FeignClient(name = "provider", fallback = UserHystrix.class)
public interface UserClient {
    @PostMapping("/v1.0/user/loginByPhone")
    public Map<String, Object> loginByPhone(@RequestParam("phone") String phone,
                                            @RequestParam("password") String password);

    @PostMapping("/v1.0/user/login")
    public Map<String, Object> loginByLogin(@RequestParam("loginName") String loginName,
                                            @RequestParam("password") String password);

    @PostMapping("/v1.0/user/password/update")
    public Map<String, Object> updatePassword(@RequestParam("userId") long userId,
                                              @RequestParam("password") String password,
                                              @RequestParam("passwordNew") String passwordNew);

    @PostMapping("/v1.0/user/password/put")
    public Map<String, Object> putPassword(@RequestParam("phone") String phone,
                                           @RequestParam("password") String password,
                                           @RequestParam("code") String code);

    @PostMapping("/v1.0/user/create")
    public Map<String, Object> addUser(@RequestBody User user);

    @PostMapping("/v1.0/password/set")
    public Map<String, Object> setPassword(@RequestParam(value = "phone", required = false) String phone,
                                           @RequestParam(value = "userId", required = false) Long userId,
                                           @RequestParam("type") Integer type,
                                           @RequestParam("password") String password);

    @PostMapping("/v1.0/user/by/houseId/{houseId}")
    public LayuiVO queryUserByHouseId(@PathVariable("houseId") Long houseId);

    @RequestMapping("/v1.0/user/query/page")
    public LayuiVO queryUser(@RequestParam(value = "userName", required = false) String userName,
                             @RequestParam(value = "phone", required = false) String phone,
                             @RequestParam(value = "state", required = false) Integer state,
                             @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageIndex,
                             @RequestParam(value = "limit", required = false, defaultValue = "20") Integer pageSize);
    @PostMapping("/v1.0/user/state/update")
    public Object stateUpdate(@RequestParam("userId") Long userId,
                              @RequestParam("state") Integer state);
}
