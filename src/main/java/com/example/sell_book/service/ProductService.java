package com.example.sell_book.service;

import com.example.sell_book.models.ProductDTO;

import java.util.List;

public interface ProductService {
    public List<ProductDTO> getList();

    public List<ProductDTO> getList(int page, int size);


    public List<ProductDTO> getListByNameLike(String name);

    public List<ProductDTO> getListOrderByPrice(int id);

    public ProductDTO getById(int id);

    public void addOrUpDate(ProductDTO productDTO);

    public void delete(int id);
}
