package com.example.sell_book.service;

import com.example.sell_book.models.TableOrderDTO;

import java.util.List;

public interface TableOrderService {
    public List<TableOrderDTO> getListByActive(boolean active);

    public TableOrderDTO getById(int id);

    public void addOrUpdate(TableOrderDTO tableOrderDTO);

    public TableOrderDTO getByAccountUsernameAndActive(String username, boolean active);

    public void delete(int id);
}
