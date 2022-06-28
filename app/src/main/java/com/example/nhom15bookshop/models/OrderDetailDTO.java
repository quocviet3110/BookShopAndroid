package com.example.nhom15bookshop.models;

public class OrderDetailDTO extends AbstractDTO<OrderDetailDTO> {
    private int bookID;
    private OrderDTO orderDetail;
    private BookDTO bookOrder;
    private int number;
    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public OrderDTO getOrderDetail() {
        return orderDetail;
    }
    public void setOrderDetail(OrderDTO orderDetail) {
        this.orderDetail = orderDetail;
    }
    public BookDTO getBookOrder() {
        return bookOrder;
    }
    public void setBookOrder(BookDTO bookOrder) {
        this.bookOrder = bookOrder;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}