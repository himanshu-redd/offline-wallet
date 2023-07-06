package com.masters.pay.offline_wallet.controller;

import com.masters.pay.offline_wallet.request.UserLogInRequest;
import com.masters.pay.offline_wallet.request.UserSignUpRequest;
import com.masters.pay.offline_wallet.response.BaseResponse;
import com.masters.pay.offline_wallet.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse userSignup(@RequestBody UserSignUpRequest userSignUpRequest){
        log.info("Sign UP request received");
        BaseResponse baseResponse = userService.userSignUp(userSignUpRequest);
        return baseResponse;
    }

    @PostMapping(path = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse userLogin(@RequestBody UserLogInRequest userLoginRequest){
        log.info("Login request received");
        BaseResponse baseResponse = userService.userLogin(userLoginRequest);
        return baseResponse;
    }
}
