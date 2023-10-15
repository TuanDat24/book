package com.example.sell_book.dao.impl;


import com.example.sell_book.dao.AccountDao;
import com.example.sell_book.entity.Account;
import com.example.sell_book.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao {


    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getList() {
        return accountRepository.findAll();
    }

    @Override
    public Account getById(int id) {

        return accountRepository.findById(id).get();
    }

    @Override
    public Account getByUsername(String username) {

        return accountRepository.findByUsername(username);
    }

    @Override
    public Account getByEmail(String email) {

        return accountRepository.findByEmail(email);
    }

    @Override
    public Account getByUsernameOrEmail(String username, String email) {

        return accountRepository.findByUsernameOrEmail(username, email);
    }

    @Override
    public Account getByUsernameAndActive(String username, boolean active) {

        return accountRepository.findByUsernameAndActive(username, active);
    }

    @Override
    public void addOrUpdate(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void delete(int id) {
        accountRepository.delete(getById(id));
    }
}
