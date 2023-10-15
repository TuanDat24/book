package com.example.sell_book.controller;


import com.example.sell_book.models.AccountDTO;
import com.example.sell_book.models.TableOrderDTO;
import com.example.sell_book.service.AccountService;
import com.example.sell_book.service.TableOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/QuanLyDonHang")
public class TableOrderController {

    @Autowired
    private TableOrderService tableOrderService;

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/DanhSach")
    public String list(HttpServletRequest request) {
        List<TableOrderDTO> tableOrderDTOs = tableOrderService.getListByActive(true);
        request.setAttribute("tableOrderDTOs", tableOrderDTOs);
        addAccount(request);
        return "admin/tableorder/list";
    }

    @GetMapping(value = "/Xoa/{id}")
    public String delete(@PathVariable("id") int id) {
        tableOrderService.delete(id);
        return "redirect:/admin/QuanLyDonHang/DanhSach";
    }

    @GetMapping(value = "/GiaoHang/{id}")
    public String checked(@PathVariable("id") int id, HttpServletRequest request) {
        TableOrderDTO tableOrderDTO = tableOrderService.getById(id);
        tableOrderDTO.setChecked(true);
        addAccount(request);
        tableOrderService.addOrUpdate(tableOrderDTO);
        return "redirect:/admin/QuanLyDonHang/DanhSach";
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
