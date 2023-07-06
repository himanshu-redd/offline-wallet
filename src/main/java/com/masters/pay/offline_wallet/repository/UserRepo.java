package com.masters.pay.offline_wallet.repository;

import com.masters.pay.offline_wallet.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Query("SELECT u from User u WHERE u.username = ?1")
    public List<User> findByUsername(String username);

}
