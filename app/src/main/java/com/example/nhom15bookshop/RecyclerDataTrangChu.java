package com.example.nhom15bookshop;

public class RecyclerDataTrangChu {
    private String title;
    private int price;
    private int imgid;

    public RecyclerDataTrangChu(String title, int price, int imgid) {
        this.title = title;
        this.price = price;
        this.imgid = imgid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }
}
