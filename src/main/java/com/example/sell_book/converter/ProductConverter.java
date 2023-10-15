package com.example.sell_book.converter;


import com.example.sell_book.entity.Product;
import com.example.sell_book.models.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    private ModelMapper modelMapper;

    public ProductConverter() {
        modelMapper = new ModelMapper();
    }

    public ProductDTO toDTO(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);

//        productDTO.setCategory(product.getCategory().getName());
//        productDTO.setProvider(product.getProvider().getName());
//        productDTO.setUnit(product.getUnit().getName());

        return productDTO;
    }

    public Product toEntity(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        return product;

    }
}
