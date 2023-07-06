package com.masters.pay.offline_wallet.repository;

import com.masters.pay.offline_wallet.dao.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

}
