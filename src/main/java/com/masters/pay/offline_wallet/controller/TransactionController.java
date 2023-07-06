package com.masters.pay.offline_wallet.controller;

import com.masters.pay.offline_wallet.dao.Transaction;
import com.masters.pay.offline_wallet.request.WalletRechargeRequest;
import com.masters.pay.offline_wallet.response.BaseResponse;
import com.masters.pay.offline_wallet.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wallet")
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @PostMapping("/recharge")
    public BaseResponse rechargeWallet(@RequestBody WalletRechargeRequest walletRechargeRequest){
        log.info("Recharge request received");
        return transactionService.rechargeWallet(walletRechargeRequest);
    }

}
