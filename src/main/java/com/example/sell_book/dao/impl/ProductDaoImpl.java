package com.example.sell_book.dao.impl;

import com.example.sell_book.dao.ProductDao;
import com.example.sell_book.entity.Product;
import com.example.sell_book.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProductDaoImpl implements ProductDao {


    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getList() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getList(int page, int size) {
        Page<Product> pageProduct = productRepository.findAll(PageRequest.of(page, size));
        return pageProduct.getContent();
    }


    @Override
    public Product getById(int id) {

        return productRepository.findById(id);
    }

    @Override
    public void addOrUpDate(Product product) {
        productRepository.save(product);

    }


    @Override
    public void delete(int id) {
        productRepository.delete(getById(id));

    }

    @Override
    public List<Product> getListByNameLike(String name) {

        return productRepository.findAllByNameLike(name);
    }

    @Override
    public List<Product> getListOrderByPrice(int id) {

        if(id == 0) {
            return productRepository.findAllByOrderByPriceAsc();
        }

        return productRepository.findAllByOrderByPriceDesc();
    }
}
