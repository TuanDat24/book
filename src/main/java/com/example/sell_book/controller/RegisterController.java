package com.example.sell_book.controller;


import com.example.sell_book.models.AccountDTO;
import com.example.sell_book.service.AccountService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RegisterController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/register")
    public String register(Model model) {
        AccountDTO accountDTO = new AccountDTO();
        model.addAttribute("account", accountDTO);
        return "Register";
    }

    @PostMapping(value = "/register")
    public String register(HttpServletRequest request, @ModelAttribute(name = "account") AccountDTO accountDTO) {
        if (!accountDTO.getPassword().equals(accountDTO.getRePassword())) {
            request.setAttribute("errorpassword", "Mật khẩu không trùng nhau");
            request.setAttribute("status", "Đăng kí thất bại");
            return "Register";
        }

        AccountDTO account = accountService.getByUsernameOrEmail(accountDTO.getUsername(), accountDTO.getEmail());

        if (account == null) {
            accountDTO.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
            List<String> roles = new ArrayList<String>();
            roles.add("User");
            accountDTO.setActive(true);
            accountDTO.setRoles(roles);

            String key = RandomStringUtils.randomAlphanumeric(10);
            accountDTO.setKey(key);

            accountService.addOrUpdate(accountDTO);

            request.setAttribute("status", "Đăng kí thành công");
        } else {
            if (account.getUsername().equalsIgnoreCase(accountDTO.getUsername())) {
                request.setAttribute("errusername", "Tài khoản đã tồn tại");
            }
            if(StringUtils.hasText(account.getEmail())){
                if (account.getEmail().equalsIgnoreCase(accountDTO.getEmail())) {
                    request.setAttribute("erremail", "Email đã tồn tại");
                }
            }

            request.setAttribute("status", "Đăng kí thất bại");
        }

        return "Register";
    }


}
