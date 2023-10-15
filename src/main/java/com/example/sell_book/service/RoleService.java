package com.example.sell_book.service;

import com.example.sell_book.models.RoleDTO;

import java.util.List;

public interface RoleService {
    public List<RoleDTO> getList();

    public RoleDTO getById(int id);

    public RoleDTO getByName(String name);

    public void addOrUpdate(RoleDTO roleDTO);

    public void delete(int id);
}
