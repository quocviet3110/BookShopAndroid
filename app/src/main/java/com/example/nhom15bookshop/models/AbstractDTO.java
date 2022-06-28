package com.example.nhom15bookshop.models;

import java.util.ArrayList;
import java.util.List;

public class AbstractDTO<T> {
    private Integer id;
    private Integer[] ids;
    private List<T> listResult = new ArrayList<>();
    private Integer page;
    private Integer limit;
    private Integer totalPage;
    private Integer totalItem;
    private String sortName;
    private String sortBy;
    private String type;


    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getSortName() {
        return sortName;
    }
    public void setSortName(String sortName) {
        this.sortName = sortName;
    }
    public String getSortBy() {
        return sortBy;
    }
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer[] getIds() {
        return ids;
    }
    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
    public List<T> getListResult() {
        return listResult;
    }
    public void setListResult(List<T> listResult) {
        this.listResult = listResult;
    }
    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    public Integer getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
    public Integer getTotalItem() {
        return totalItem;
    }
    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

}
