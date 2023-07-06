package com.masters.pay.offline_wallet.repository;

import com.masters.pay.offline_wallet.dao.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Long> {
    @Query("SELECT w FROM Wallet w where w.username = ?1")
    public Optional<Wallet> findByUsername(String username);
}
