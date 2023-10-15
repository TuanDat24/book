package com.example.sell_book.repository;

import com.example.sell_book.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findByName(String name);
}
