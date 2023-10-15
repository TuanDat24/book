package com.example.sell_book.controller;


import com.example.sell_book.other.ProcessUrlImage;
import com.example.sell_book.other.UpLoadFile;
import com.example.sell_book.service.AccountService;
import com.example.sell_book.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import com.example.sell_book.models.AccountDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/QuanLyTaiKhoan")
public class AccountController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/DanhSach")
    public String list(HttpServletRequest request) {
        List<AccountDTO> accountDTOs = accountService.getList();
        request.setAttribute("list", accountDTOs);
        addAccount(request);
        return "admin/account/list";
    }

    @GetMapping(value = "/ChiTiet/{id}")
    public String detail(HttpServletRequest request, @PathVariable(name = "id") int id) {

        AccountDTO accountDTO = accountService.getById(id);

        request.setAttribute("account", accountDTO);
        addAccount(request);
        return "admin/account/detail";
    }

    @GetMapping(value = "/Them")
    public String add(Model model, HttpServletRequest request) {
        AccountDTO accountDTO = new AccountDTO();

        model.addAttribute("account", accountDTO);
        model.addAttribute("roles", roleService.getList());
        addAccount(request);
        return "admin/account/formAdd";
    }

    @PostMapping(value = "/Them")
    public String add(@ModelAttribute(name = "account") AccountDTO accountDTO, @RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
        String urlImage = ProcessUrlImage.processUrlImage(multipartFile.getOriginalFilename(), String.valueOf(System.currentTimeMillis()));
        UpLoadFile.saveFile(multipartFile, urlImage);

        accountDTO.setAvatar(urlImage);
        accountDTO.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        addAccount(request);
        accountService.addOrUpdate(accountDTO);
        return "redirect:/admin/QuanLyTaiKhoan/DanhSach";
    }

    @GetMapping(value = "/Sua/{id}")
    public String update(Model model, @PathVariable(name = "id") int id, HttpServletRequest request) {
        AccountDTO accountDTO = accountService.getById(id);
        addAccount(request);
        model.addAttribute("account", accountDTO);
        model.addAttribute("roles", roleService.getList());
        return "admin/account/formUpdate";
    }

    @PostMapping(value = "/Sua")
    public String update(@ModelAttribute(name = "account") AccountDTO accountDTO, @RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
        UpLoadFile.deleteFile(accountService.getById(accountDTO.getId()).getAvatar());
        addAccount(request);

        String urlImage = ProcessUrlImage.processUrlImage(multipartFile.getOriginalFilename(), String.valueOf(System.currentTimeMillis()));
        UpLoadFile.saveFile(multipartFile, urlImage);

        accountDTO.setAvatar(urlImage);

        accountDTO.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        accountService.addOrUpdate(accountDTO);

        return "redirect:/admin/QuanLyTaiKhoan/DanhSach";
    }

    @GetMapping(value = "/Xoa/{id}")
    public String delete(HttpServletRequest request, @PathVariable(name = "id") int id) {

        accountService.delete(id);

        return "redirect:/admin/QuanLyTaiKhoan/DanhSach";
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
