package com.masters.pay.offline_wallet.service;

import com.masters.pay.offline_wallet.Session.SessionManager;
import com.masters.pay.offline_wallet.Util.UnifiedUtil;
import com.masters.pay.offline_wallet.dao.User;
import com.masters.pay.offline_wallet.repository.TransactionRepo;
import com.masters.pay.offline_wallet.request.DebitFromBankRequest;
import com.masters.pay.offline_wallet.request.WalletRechargeRequest;
import com.masters.pay.offline_wallet.response.BaseResponse;
import com.masters.pay.offline_wallet.response.DebitFromBankResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private SessionManager sessionManager;
    @Autowired
    private WebClient.Builder webClientBuilder;

    public BaseResponse rechargeWallet(WalletRechargeRequest walletRechargeRequest) {
        User loggedInUser = sessionManager.getLoggedInUser();
        if (loggedInUser != null) {
            String emailId = loggedInUser.getEmailId();
            Long amount = walletRechargeRequest.getAmount();
            DebitFromBankRequest request = new DebitFromBankRequest();
            request.setAmount(amount);
            request.setEmailId(emailId);
            DebitFromBankResponse responseBody = webClientBuilder.build()
                    .post()
                    .uri(UnifiedUtil.bankDebitUrl)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(DebitFromBankResponse.class)
                    .block();
            if (responseBody == null) {
                log.info("Response body empty");
                UnifiedUtil.getBaseRepWithFailedStatus("400", "Response body empty");
            } else {
                log.info("Response body : " + responseBody);
                UnifiedUtil.getBaseResponseWithSuccessStatus("400", "Response body received");
            }
        }
        return UnifiedUtil.getBaseRepWithFailedStatus("400", "Login First");
    }
}
