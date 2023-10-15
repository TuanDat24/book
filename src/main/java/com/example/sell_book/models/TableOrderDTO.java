package com.example.sell_book.models;

import java.util.List;

public class TableOrderDTO {

    private int id;

    private String name;

    private boolean active;

    private boolean checked;

    private AccountDTO accountDTO;

    private List<ProductOrderDTO> productOrderDTOs;

    public int getTotalMoney() {
        int total = 0;
        for (ProductOrderDTO productOrderDTO : productOrderDTOs) {
            total += productOrderDTO.getToTalMoney();
        }

        return total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public AccountDTO getAccountDTO() {
        return accountDTO;
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
    }

    public List<ProductOrderDTO> getProductOrderDTOs() {
        return productOrderDTOs;
    }

    public void setProductOrderDTOs(List<ProductOrderDTO> productOrderDTOs) {
        this.productOrderDTOs = productOrderDTOs;
    }

}
