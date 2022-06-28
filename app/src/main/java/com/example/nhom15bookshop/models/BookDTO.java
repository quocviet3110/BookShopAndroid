package com.example.nhom15bookshop.models;

public class BookDTO extends AbstractDTO<BookDTO>{
    private String name;
    private String title;
    private int number;
    private int idAuthor;
    private int idCategory;
    private int idPublisher;
    private AuthorDTO authors;
    private PublisherDTO publishers;
    private CategoryDTO category;
    private String urlImage;
    private Float price;



    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
    public String getUrlImage() {
        return urlImage;
    }
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public AuthorDTO getAuthors() {
        return authors;
    }
    public void setAuthors(AuthorDTO authors) {
        this.authors = authors;
    }
    public PublisherDTO getPublishers() {
        return publishers;
    }
    public void setPublishers(PublisherDTO publishers) {
        this.publishers = publishers;
    }


    public CategoryDTO getCategory() {
        return category;
    }
    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
    public int getIdAuthor() {
        return idAuthor;
    }
    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }
    public int getIdCategory() {
        return idCategory;
    }
    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
    public int getIdPublisher() {
        return idPublisher;
    }
    public void setIdPublisher(int idPublisher) {
        this.idPublisher = idPublisher;
    }

    public BookDTO(String name) {
        this.name = name;
    }

    public BookDTO(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public BookDTO() {
    }
}
