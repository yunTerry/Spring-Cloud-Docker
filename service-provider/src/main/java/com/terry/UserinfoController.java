package com.terry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/***
 * *
 * 名称：     DcController.
 * 作者：     Terry Tan
 * 创建时间：  on 2017/6/29.
 * 说明：     
 * *
 ***/

@RestController
public class UserinfoController {

    @GetMapping("/userinfo")
    public BaseResponse getUserinfo() {
        BaseResponse baseResponse = new BaseResponse();
        User user = new User();
        user.id = UUID.randomUUID().toString();
        user.age = 19;
        user.img = "http://up.qqjia.com/z/16/tu17317_45.png";
        user.name = "Merry li";
        user.sex = "girl";
        user.qianmin="The future is already here. It’s just not evenly distributed yet";
        baseResponse.data = user;
        baseResponse.code = 200;
        baseResponse.message = "This is from eureka service provider 1";
        String json = "";
        try {
            json = new ObjectMapper().writerWithDefaultPrettyPrinter()
                    .writeValueAsString(baseResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);
        return baseResponse;
    }

}