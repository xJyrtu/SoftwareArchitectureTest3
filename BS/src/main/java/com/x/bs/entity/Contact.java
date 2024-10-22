package com.x.bs.entity;

public class Contact {
    private String name;
    private String phone;
    private String addr;

    // 构造函数、getter和setter
    public Contact(String name, String phone, String addr) {
        this.name = name;
        this.phone = phone;
        this.addr = addr;
    }

    // Getter 和 Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddr() { return addr; }
    public void setAddr(String addr) { this.addr = addr; }
}
