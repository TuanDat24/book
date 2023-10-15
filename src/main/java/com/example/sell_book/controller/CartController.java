package com.example.sell_book.controller;


import com.example.sell_book.models.TableOrderDTO;
import com.example.sell_book.service.CartService;
import com.example.sell_book.service.TableOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    private TableOrderService tableOrderService;

    @Autowired
    private CartService cartService;

    @GetMapping(value = "/view")
    public String viewCart(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        request.setAttribute("authentication",authentication.getName());
        TableOrderDTO tableOrderDTO = tableOrderService.getByAccountUsernameAndActive(authentication.getName(), false);

        if (tableOrderDTO == null) {
            return "cart/notCart";
        }

        request.setAttribute("order", tableOrderDTO);

        return "cart/view";
    }

    @GetMapping(value = "/add/{idProduct}")
    public String addCart(HttpServletRequest request, @PathVariable("idProduct") int idProduct) {
        cartService.addCart(idProduct, 1);
        return "redirect:/home";
    }

    @GetMapping(value = "/addProduct/{idProduct}")
    public String addCart1(HttpServletRequest request, @PathVariable("idProduct") int idProduct,
                           @RequestParam("number") int number) {
        cartService.addCart(idProduct, number);
        return "redirect:/detailproduct/" + idProduct;
    }

    @GetMapping(value = "/increase/{idProduct}")
    public String increaseCart(HttpServletRequest request, @PathVariable("idProduct") int idProduct) {
        cartService.addCart(idProduct, 1);
        return "redirect:/cart/view";
    }

    @GetMapping(value = "/delete/{idProductOrder}")
    public String deleteCart(HttpServletRequest request, @PathVariable("idProductOrder") int idProductOrder) {
        cartService.deletedCart(idProductOrder);
        return "redirect:/cart/view";
    }

    @GetMapping(value = "/reduce/{idProductOrder}")
    public String reduceCart(HttpServletRequest request, @PathVariable("idProductOrder") int idProductOrder) {
        cartService.reduceCart(idProductOrder);

        return "redirect:/cart/view";
    }

    @GetMapping(value = "/buy")
    public String buyCart(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        request.setAttribute("authentication",authentication.getName());
        TableOrderDTO tableOrderDTO = tableOrderService
                .getByAccountUsernameAndActive(SecurityContextHolder.getContext().getAuthentication().getName(), false);
        tableOrderDTO.setActive(true);
        tableOrderDTO.setChecked(false);
        request.setAttribute("order", tableOrderDTO);
        tableOrderService.addOrUpdate(tableOrderDTO);
        return "cart/buy";
    }
}
