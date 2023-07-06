package com.masters.pay.offline_wallet.response;

import com.masters.pay.offline_wallet.dao.Transaction;
import lombok.Data;

@Data
public class TransactionListResponse extends BaseResponse{
    Transaction[] transactions;
}
