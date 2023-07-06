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
@Table(name = "wallet")
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CreationTimestamp
    private Instant createdTime;
    @UpdateTimestamp
    private Instant updatedTime;
    private String username;
    private Long balance;
    private String lastTxnType;
    private Long lastTxnValue;
}
