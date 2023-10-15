package com.example.sell_book.controller;


import com.example.sell_book.models.AccountDTO;
import com.example.sell_book.models.ProductDTO;
import com.example.sell_book.other.ProcessUrlImage;
import com.example.sell_book.other.UpLoadFile;
import com.example.sell_book.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/QuanLySanPham/")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/DanhSach")
    public String listProduct(HttpServletRequest request) {
        List<ProductDTO> listProductDTOs = productService.getList();
        request.setAttribute("list", listProductDTOs);
        addAccount(request);
        return "admin/product/list";
    }

    @GetMapping(value = "/ChiTiet/{id}")
    public String detailProduct(HttpServletRequest request, @PathVariable("id") int id) {
        ProductDTO productDTO = productService.getById(id);
        request.setAttribute("product", productDTO);
        addAccount(request);
        return "admin/product/detail";
    }

    @GetMapping(value = "/Them")
    public String addProduct(HttpServletRequest request,Model model) {
        ProductDTO productDTO = new ProductDTO();
        model.addAttribute("product", productDTO);
        addAccount(request);
        return "admin/product/formAdd";
    }

    @PostMapping(value = "/Them")
    public String addProduct(@ModelAttribute("product") ProductDTO productDTO, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String urlImage = ProcessUrlImage.processUrlImage(file.getOriginalFilename(), String.valueOf(System.currentTimeMillis()));
        UpLoadFile.saveFile(file, urlImage);
        productDTO.setUrl_image(urlImage);
        productService.addOrUpDate(productDTO);
        addAccount(request);
        System.out.print(productDTO);
        return "redirect:/admin/QuanLySanPham/DanhSach";
    }

    @GetMapping(value = "/Sua/{id}")
    public String updateProduct(Model model, @PathVariable("id") int id, HttpServletRequest request) {
        ProductDTO productDTO = productService.getById(id);
        model.addAttribute("product", productDTO);
        addAccount(request);
        return "admin/product/formUpdate";
    }

    @PostMapping(value = "/Sua")
    public String updateProduct(@ModelAttribute("product") ProductDTO productDTO, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        UpLoadFile.deleteFile(productService.getById(productDTO.getId()).getUrl_image());
        String urlImage = ProcessUrlImage.processUrlImage(file.getOriginalFilename(), String.valueOf(System.currentTimeMillis()));
        UpLoadFile.saveFile(file, urlImage);
        productDTO.setUrl_image(urlImage);
        productService.addOrUpDate(productDTO);
        addAccount(request);
        return "redirect:/admin/QuanLySanPham/DanhSach";
    }

    @GetMapping(value = "/Xoa/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        ProductDTO productDTO = productService.getById(id);
        UpLoadFile.deleteFile(productDTO.getUrl_image());
        productService.delete(id);
        return "redirect:/admin/QuanLySanPham/DanhSach";
    }
    private void addAccount(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountDTO accountDTO = new AccountDTO();
        if(!authentication.getName().equalsIgnoreCase("anonymousUser")) {
            request.setAttribute("authentication", authentication.getName());
            accountDTO = accountService.getByUsername(authentication.getName());
            request.setAttribute("urlImage", accountDTO.getAvatar());
        }
    }
}
