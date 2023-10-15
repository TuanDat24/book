package com.example.sell_book.service;

import com.example.sell_book.models.AccountDTO;

import java.util.List;

public interface AccountService {

    public List<AccountDTO> getList();

    public AccountDTO getById(int id);

    public AccountDTO getByUsername(String username);

    public AccountDTO getByEmail(String email);

    public AccountDTO getByUsernameOrEmail(String username, String email);

    public AccountDTO getByUsernameAndActive(String username, boolean active);

    public void addOrUpdate(AccountDTO accountDTO);

    public void delete(int id);
}
