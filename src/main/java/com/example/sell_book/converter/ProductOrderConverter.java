package com.example.sell_book.converter;


import com.example.sell_book.dao.ProductDao;
import com.example.sell_book.entity.Product;
import com.example.sell_book.entity.ProductOrder;
import com.example.sell_book.entity.TableOrder;
import com.example.sell_book.models.ProductDTO;
import com.example.sell_book.models.ProductOrderDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductOrderConverter {
    private ModelMapper modelMapper;
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductConverter productConverter;

    public ProductOrderConverter() {
        modelMapper = new ModelMapper();
    }

    public ProductOrder toEntity(ProductOrderDTO productOrderDTO, TableOrder tableOrder) {
        ProductOrder productOrder = modelMapper.map(productOrderDTO, ProductOrder.class);

        productOrder.setProduct(productDao.getById(productOrderDTO.getProductDTO().getId()));
        productOrder.setTableOrder(tableOrder);
        return productOrder;
    }

    public ProductOrderDTO toDTO(ProductOrder productOrder) {
        ProductOrderDTO productOrderDTO = modelMapper.map(productOrder, ProductOrderDTO.class);

        Product product = productOrder.getProduct();
        ProductDTO productDTO = productConverter.toDTO(product);
        productOrderDTO.setProductDTO(productDTO);

        return productOrderDTO;
    }

}
