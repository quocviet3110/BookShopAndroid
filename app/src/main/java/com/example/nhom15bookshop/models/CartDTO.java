package com.example.nhom15bookshop.models;

public class CartDTO extends AbstractDTO<CartDTO> {

    private int  number;
    private CustomerDTO customerCart;
    private BookDTO bookCart;
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public CustomerDTO getCustomerCart() {
        return customerCart;
    }
    public void setCustomerCart(CustomerDTO customerCart) {
        this.customerCart = customerCart;
    }
    public BookDTO getBookCart() {
        return bookCart;
    }
    public void setBookCart(BookDTO bookCart) {
        this.bookCart = bookCart;
    }

}
