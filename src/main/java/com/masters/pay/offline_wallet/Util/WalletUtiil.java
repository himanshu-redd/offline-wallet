package com.masters.pay.offline_wallet.Util;

import com.masters.pay.offline_wallet.dao.User;
import com.masters.pay.offline_wallet.dao.Wallet;
import com.masters.pay.offline_wallet.response.BaseResponse;
import com.masters.pay.offline_wallet.response.CheckBalanceResponse;

public class WalletUtiil extends UnifiedUtil {
    public static BaseResponse getCheckBalanceResponse(String code, String message, Wallet wallet) {
        CheckBalanceResponse response = new CheckBalanceResponse();
        response.setStatus("200");
        response.setCode(code);
        response.setMessage(message);
        response.setUsername(wallet.getUsername());
        response.setWalletBalance(wallet.getBalance());
        response.setLastTxnType(wallet.getLastTxnType());
        response.setLastTxnValue(wallet.getLastTxnValue());
        return response;
    }
}
