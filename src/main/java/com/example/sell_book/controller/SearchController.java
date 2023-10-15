package com.example.sell_book.controller;

import com.example.sell_book.models.ProductDTO;
import com.example.sell_book.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/searchInput")
    public String search(HttpServletRequest request, @RequestParam("name") String name) {
        String result = "%"+name+"%";

        List<ProductDTO> productDTOs = productService.getListByNameLike(result);
        request.setAttribute("listProduct", productDTOs);

        return "user/searchInput";
    }

    @GetMapping(value = "/searchProductSort")
    public String selectProductSort(HttpServletRequest request, @RequestParam("id") int id) {


        List<ProductDTO> productDTOs = productService.getListOrderByPrice(id);
        request.setAttribute("listProduct", productDTOs);

        return "user/selectProductSort";
    }
}
