package com.masters.pay.offline_wallet.dao;

import com.masters.pay.offline_wallet.enums.TxnType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CreationTimestamp
    private Instant createdTime;
    @UpdateTimestamp
    private Instant updatedTime;
    private TxnType txnType;
    private String senderUsername;
    private String receiverUsername;
    private Long amount;
}
