package com.masters.pay.offline_wallet.response;

import lombok.Data;

@Data
public class CheckBalanceResponse extends BaseResponse{
    private String username;
    private Long walletBalance;
    private String lastTxnType;
    private Long lastTxnValue;
}
