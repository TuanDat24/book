package com.example.sell_book.controller;


import com.example.sell_book.models.AccountDTO;
import com.example.sell_book.models.ProductDTO;
import com.example.sell_book.service.AccountService;
import com.example.sell_book.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping(value = "/home")
    public String home(HttpServletRequest request) {

        int page = 0;
        request.setAttribute("page", page);
        List<ProductDTO> productDTOs = productService.getList(page,12);
        request.setAttribute("listProduct", productDTOs);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!authentication.getName().equalsIgnoreCase("anonymousUser")) {
            request.setAttribute("authentication", authentication.getName());
        }
        return "home";
    }

    @GetMapping(value = "/home/pages")
    public String homePage(HttpServletRequest request, @RequestParam("page")int page) {

        List<ProductDTO> productDTOs = productService.getList(page-1,12);
        request.setAttribute("listProduct", productDTOs);


        request.setAttribute("page", page);
        return "home";
    }


    @GetMapping(value = "/403")
    public String err403() {
        return "403";
    }

    @GetMapping(value = "/admin")
    public String homeAdmin(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountDTO accountDTO = new AccountDTO();
        if(!authentication.getName().equalsIgnoreCase("anonymousUser")) {
            request.setAttribute("authentication", authentication.getName());
            accountDTO = accountService.getByUsername(authentication.getName());
            request.setAttribute("urlImage", accountDTO.getAvatar());
        }
        return "admin";
    }
}
