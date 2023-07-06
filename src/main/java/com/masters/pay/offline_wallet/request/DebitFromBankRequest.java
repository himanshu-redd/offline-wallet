package com.masters.pay.offline_wallet.request;

import lombok.Data;

@Data
public class DebitFromBankRequest {
    private String emailId;
    private Long amount;
}
