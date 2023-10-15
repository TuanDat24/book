package com.example.sell_book.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<Account> accounts;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
