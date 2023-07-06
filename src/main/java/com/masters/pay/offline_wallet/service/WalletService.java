package com.masters.pay.offline_wallet.service;

import com.masters.pay.offline_wallet.Session.SessionManager;
import com.masters.pay.offline_wallet.Util.WalletUtiil;
import com.masters.pay.offline_wallet.dao.User;
import com.masters.pay.offline_wallet.dao.Wallet;
import com.masters.pay.offline_wallet.enums.TxnType;
import com.masters.pay.offline_wallet.repository.UserRepo;
import com.masters.pay.offline_wallet.repository.WalletRepo;
import com.masters.pay.offline_wallet.response.BaseResponse;
import com.masters.pay.offline_wallet.response.CheckBalanceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class WalletService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    WalletRepo walletRepo;
    @Autowired
    SessionManager sessionManager;
    WalletUtiil walletUtiil;

    public void createWallet(String username) {
        Wallet wallet = new Wallet();
        wallet.setBalance(0L);
        wallet.setUsername(username);
        wallet.setLastTxnType(TxnType.NA.name());
        wallet.setLastTxnValue(0L);
        walletRepo.save(wallet);
        log.info("Wallet Created for user : " + username);
    }

    public BaseResponse checkBalance() {
        if (sessionManager.getLoggedInUser() == null) {
            return WalletUtiil.getBaseRepWithFailedStatus("400", "Login to the wallet first");
        } else {
            User loggedInUser = sessionManager.getLoggedInUser();
            Optional<Wallet> resultSet = walletRepo.findByUsername(loggedInUser.getUsername());
            if (resultSet.isPresent()) {
                Wallet wallet = resultSet.get();
                return WalletUtiil.getCheckBalanceResponse("200", "Wallet present", wallet);
            } else {
                return WalletUtiil.getBaseRepWithFailedStatus("400", "Wallet Not Found");
            }
        }
    }
}