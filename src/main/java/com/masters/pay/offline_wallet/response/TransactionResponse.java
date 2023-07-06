package com.masters.pay.offline_wallet.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private Instant txnTime;
    private Instant amount;
    private String sender;
    private String receiver;
}
