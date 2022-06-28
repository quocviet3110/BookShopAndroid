package com.example.nhom15bookshop.models;

public class BookImageDTO extends AbstractDTO<BookImageDTO> {

    private String name;
    private String url;
    private BookDTO books;
    private int idBook;

    public int getIdBook() {
        return idBook;
    }
    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public BookDTO getBooks() {
        return books;
    }
    public void setBooks(BookDTO books) {
        this.books = books;
    }


}