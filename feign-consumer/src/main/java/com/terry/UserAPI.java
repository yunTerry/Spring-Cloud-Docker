package com.terry;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/***
 * *
 * 名称：     UserAPI.
 * 作者：     Terry Tan
 * 创建时间：  on 2017/7/7.
 * 说明：     
 * *
 ***/

@FeignClient("userinfo-microservice")
public interface UserAPI {

    @GetMapping("/userinfo")
    BaseResponse getUserinfo();

}
