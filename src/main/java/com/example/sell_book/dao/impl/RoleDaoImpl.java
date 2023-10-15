package com.example.sell_book.dao.impl;


import com.example.sell_book.dao.RoleDao;
import com.example.sell_book.entity.Role;
import com.example.sell_book.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getList() {
        List<Role> roles = roleRepository.findAll();
        return roles;
    }

    @Override
    public Role getById(int id) {
        Role role = roleRepository.findById(id).get();
        return role;
    }

    @Override
    public Role getByName(String name) {
        Role role = roleRepository.findByName(name);
        return role;
    }


    @Override
    public void addOrUpDate(Role role) {
        roleRepository.save(role);

    }

    @Override
    public void delete(int id) {
        roleRepository.delete(getById(id));
    }
}
