package com.example.sell_book.repository;


import com.example.sell_book.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AccountRepository extends JpaRepository<Account, Integer> {


    Account findByUsername(String username);

    Account findByEmail(String email);

    Account findByUsernameOrEmail(String username, String email);

    Account findByUsernameAndActive(String username, boolean active);
}
