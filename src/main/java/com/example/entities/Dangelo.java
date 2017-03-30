package com.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dangelo {

    private String address;
    private String email;
    private int id;
    private String name;
    private String phonenumber;
    private String ssn;

    //private String address;
    private String cellphone;
    //private String email;
    //private int id; might actually need this
    //private String name;
    private String servicebranch;

    public Dangelo(){}

    public Dangelo(String address, String email, int id, String name, String phonenumber, String ssn, String cellphone, String servicebranch) {
        this.address = address;
        this.email = email;
        this.id = id;
        this.name = name;
        this.phonenumber = phonenumber;
        this.ssn = ssn;
        this.cellphone = cellphone;
        this.servicebranch = servicebranch;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getServicebranch() {
        return servicebranch;
    }

    public void setServicebranch(String servicebranch) {
        this.servicebranch = servicebranch;
    }
}
