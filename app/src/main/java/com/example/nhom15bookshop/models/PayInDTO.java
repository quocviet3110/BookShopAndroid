package com.example.nhom15bookshop.models;

import java.util.Date;

public class PayInDTO extends AbstractDTO<PayInDTO>{
    private Date date;
    private int idStaff;
    private int idSupplier;
    private StaffDTO staffPayIn;
    private SupplierDTO supplierPayIn;
    private OrderDetailDTO orderDetail;
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public StaffDTO getStaffPayIn() {
        return staffPayIn;
    }
    public void setStaffPayIn(StaffDTO staffPayIn) {
        this.staffPayIn = staffPayIn;
    }
    public SupplierDTO getSupplierPayIn() {
        return supplierPayIn;
    }
    public void setSupplierPayIn(SupplierDTO supplierPayIn) {
        this.supplierPayIn = supplierPayIn;
    }
    public OrderDetailDTO getOrderDetail() {
        return orderDetail;
    }
    public void setOrderDetail(OrderDetailDTO orderDetail) {
        this.orderDetail = orderDetail;
    }
    public int getIdStaff() {
        return idStaff;
    }
    public void setIdStaff(int idStaff) {
        this.idStaff = idStaff;
    }
    public int getIdSupplier() {
        return idSupplier;
    }
    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }


}