package com.example.nhom15bookshop.models;

public class StaffDTO extends AbstractDTO<StaffDTO> {
    private String name;
    private String address;
    private String avatar;
    private int gender;
    private String phone;
    private String email;
    private UserDTO userStaff;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public int getGender() {
        return gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public UserDTO getUserStaff() {
        return userStaff;
    }
    public void setUserStaff(UserDTO userStaff) {
        this.userStaff = userStaff;
    }

}