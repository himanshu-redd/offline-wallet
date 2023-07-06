package com.masters.pay.offline_wallet.controller;

import com.masters.pay.offline_wallet.response.BaseResponse;
import com.masters.pay.offline_wallet.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    WalletService walletService;
    @GetMapping(path = "/checkbalance", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse checkWalletBalance(){
        log.info("Check wallet balance request received");
        return walletService.checkBalance();
    }
}
