package com.terry;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * *
 * 名称：     UserController.
 * 作者：     Terry Tan
 * 创建时间：  on 2017/7/7.
 * 说明：     
 * *
 ***/

@RestController
public class UserController {

    @Autowired
    UserAPI userAPI;

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/user")
    BaseResponse getUserinfo() {
        BaseResponse response = userAPI.getUserinfo();
        response.message = "This is from eureka service consumer";
        return response;
    }

    private BaseResponse fallback() {
        BaseResponse response = new BaseResponse();
        response.code = 299;
        response.message = "服务容错保护";
        response.data = "Hystrix服务降级";
        return response;
    }

}
