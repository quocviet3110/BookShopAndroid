package com.example.nhom15bookshop.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDTO extends AbstractDTO<OrderDTO> {
    private Date date;
    private StaffDTO staffOrder;
    private CustomerDTO customerOrder;
    private StoreDTO storeOrder;
    private OrderDetailDTO orderDetail;
    private int idCustomer;
    private String status;
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
    private String convertDate;

    private List<OrderDetailDTO> listOrder = new ArrayList<>();

    public List<OrderDetailDTO> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<OrderDetailDTO> listOrder) {
        this.listOrder = listOrder;
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public StaffDTO getStaffOrder() {
        return staffOrder;
    }
    public void setStaffOrder(StaffDTO staffOrder) {
        this.staffOrder = staffOrder;
    }
    public CustomerDTO getCustomerOrder() {
        return customerOrder;
    }
    public void setCustomerOrder(CustomerDTO customerOrder) {
        this.customerOrder = customerOrder;
    }

    public StoreDTO getStoreOrder() {
        return storeOrder;
    }
    public void setStoreOrder(StoreDTO storeOrder) {
        this.storeOrder = storeOrder;
    }
    public OrderDetailDTO getOrderDetail() {
        return orderDetail;
    }
    public void setOrderDetail(OrderDetailDTO orderDetail) {
        this.orderDetail = orderDetail;
    }
    public int getIdCustomer() {
        return idCustomer;
    }
    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }


}