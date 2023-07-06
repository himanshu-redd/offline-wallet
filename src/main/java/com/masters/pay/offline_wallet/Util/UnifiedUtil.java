package com.masters.pay.offline_wallet.Util;

import com.masters.pay.offline_wallet.dao.User;
import com.masters.pay.offline_wallet.response.BaseResponse;
import com.masters.pay.offline_wallet.response.CheckBalanceResponse;

public class UnifiedUtil{
    public static String bankDebitUrl = "http://localhost:8083/bank/debit";
    public static BaseResponse getBaseRepWithFailedStatus(String code, String message) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus("false");
        baseResponse.setCode(code);
        baseResponse.setMessage(message);
        return baseResponse;
    }

    public static BaseResponse getBaseResponseWithSuccessStatus(String code, String message) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus("true");
        baseResponse.setCode(code);
        baseResponse.setMessage(message);
        return baseResponse;
    }

    public static BaseResponse getCheckBalanceResponse(String code, String message, User user) {
        CheckBalanceResponse response = new CheckBalanceResponse();
        response.setStatus("200");
        response.setCode(code);
        response.setMessage(message);
        response.setUsername(user.getUsername());
        response.setWalletBalance(user.getWalletBalance());
        return response;
    }
}
