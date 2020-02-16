package com.hes.fintech.usercontrol.repository;

import com.hes.fintech.usercontrol.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {
    Optional<UserAccount> findByUsername(String username);

    List<UserAccount> findAllByUsername(String username);

}
