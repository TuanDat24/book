package com.example.sell_book.models;

public class ProductOrderDTO {
    private int id;
    private int number;
    private ProductDTO productDTO;

    public int getToTalMoney() {
        return number * productDTO.getPrice();
    }

    public ProductOrderDTO() {
        super();
    }

    public ProductOrderDTO(int number, ProductDTO productDTO) {
        super();
        this.number = number;
        this.productDTO = productDTO;
    }

    public ProductOrderDTO(int id, int number, ProductDTO productDTO) {
        super();
        this.id = id;
        this.number = number;
        this.productDTO = productDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }
}
