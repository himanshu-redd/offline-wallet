package com.masters.pay.offline_wallet.response;

import lombok.Data;

@Data
public class DebitFromBankResponse {
    private String status;
    private String message;
}
