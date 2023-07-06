package com.masters.pay.offline_wallet.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BaseResponse {
    private String status;
    private String Code;
    private String Message;
}
