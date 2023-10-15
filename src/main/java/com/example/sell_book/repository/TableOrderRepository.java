package com.example.sell_book.repository;

import com.example.sell_book.entity.TableOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableOrderRepository extends JpaRepository<TableOrder, Integer> {
    public TableOrder findByAccountUsernameAndActive(String username, boolean active);

    public List<TableOrder> findAllByActive(boolean active);
}
