package com.example.nhom15bookshop.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentDTO extends AbstractDTO<CommentDTO> {
    private String content;
    private BookDTO bookComment;
    private CustomerDTO customerComment;
    private Date date;
    private String username;
    private int idBook;
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
    private String convertDate;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }



    public Date getDate() {
        return date;
    }
    public String getConvertDate() {
        this.convertDate= dt.format(date);
        return convertDate;
    }
    public void setConvertDate(String convertDate) {
        try {
            this.date = dt.parse(convertDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BookDTO getBookComment() {
        return bookComment;
    }

    public void setBookComment(BookDTO bookComment) {
        this.bookComment = bookComment;
    }

    public CustomerDTO getCustomerComment() {
        return customerComment;
    }

    public void setCustomerComment(CustomerDTO customerComment) {
        this.customerComment = customerComment;
    }

    public CommentDTO() {
    }
}