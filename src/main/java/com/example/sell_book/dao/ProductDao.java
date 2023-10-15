package com.example.sell_book.dao;

import com.example.sell_book.entity.Product;

import java.util.List;

public interface ProductDao {

    public List<Product> getList();

    public List<Product> getList(int page, int size);

    public List<Product> getListByNameLike(String name);

    public List<Product> getListOrderByPrice(int id);


    public Product getById(int id);

    public void addOrUpDate(Product product);

    public void delete(int id);
}
