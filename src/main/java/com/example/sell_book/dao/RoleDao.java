package com.example.sell_book.dao;

import com.example.sell_book.entity.Role;

import java.util.List;

public interface RoleDao {

    public List<Role> getList();

    public Role getById(int id);

    public Role getByName(String name);

    public void addOrUpDate(Role role);

    public void delete(int id);
}
