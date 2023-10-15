package com.example.sell_book.service.impl;


import com.example.sell_book.converter.ProductConverter;
import com.example.sell_book.dao.ProductDao;
import com.example.sell_book.entity.Product;
import com.example.sell_book.models.ProductDTO;
import com.example.sell_book.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductConverter productConverter;

    @Override
    public List<ProductDTO> getList() {
        List<Product> listproProducts = productDao.getList();
        List<ProductDTO> listproProductDTOs = new ArrayList<>();

        for (Product product : listproProducts) {
            ProductDTO productDTO = productConverter.toDTO(product);
            listproProductDTOs.add(productDTO);
        }

        return listproProductDTOs;
    }

    @Override
    public List<ProductDTO> getList(int page, int size) {
        List<Product> listproProducts = productDao.getList(page, size);
        List<ProductDTO> listproProductDTOs = new ArrayList<>();

        for (Product product : listproProducts) {
            ProductDTO productDTO = productConverter.toDTO(product);
            listproProductDTOs.add(productDTO);
        }

        return listproProductDTOs;
    }

    @Override
    public ProductDTO getById(int id) {
        Product product = productDao.getById(id);
        ProductDTO productDTO = productConverter.toDTO(product);
        return productDTO;
    }

    @Override
    public void addOrUpDate(ProductDTO productDTO) {
        Product product = productConverter.toEntity(productDTO);
        productDao.addOrUpDate(product);

    }

    @Override
    public void delete(int id) {
        productDao.delete(id);

    }

    @Override
    public List<ProductDTO> getListByNameLike(String name) {
        List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();

        List<Product> products = productDao.getListByNameLike(name);
        for (Product product : products) {
            ProductDTO productDTO = productConverter.toDTO(product);
            productDTOs.add(productDTO);
        }

        return productDTOs;
    }

    @Override
    public List<ProductDTO> getListOrderByPrice(int id) {
        List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();

        List<Product> products = productDao.getListOrderByPrice(id);
        for (Product product : products) {
            ProductDTO productDTO = productConverter.toDTO(product);
            productDTOs.add(productDTO);
        }

        return productDTOs;
    }
}
