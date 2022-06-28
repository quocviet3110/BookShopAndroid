package com.example.nhom15bookshop.models;

public class PayInDetailDTO extends AbstractDTO<PayInDetailDTO> {
    private int bookID;
    private PayInDTO payInDetail;
    private BookDTO bookPayIn;
    private int number;
    private float price;

    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public PayInDTO getPayInDetail() {
        return payInDetail;
    }
    public void setPayInDetail(PayInDTO payInDetail) {
        this.payInDetail = payInDetail;
    }
    public BookDTO getBookPayIn() {
        return bookPayIn;
    }
    public void setBookPayIn(BookDTO bookPayIn) {
        this.bookPayIn = bookPayIn;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }




}