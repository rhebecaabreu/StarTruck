package com.example.progmobile.startruck.model.bean;

public class Driver {
    private int id;
    private String Name;
    private String RG;
    private String email;
    private String CPF;
    private String Phone;
    private int CNHnumber;
    private String catCnh;
    private int userId;

    public Driver(String Name, String cpf) {
        this.Name = Name;
        this.CPF = cpf;
    }

    public Driver() {

    }

    public Driver(String name, String RG, String email, String CPF, String phone, int CNHnumber, String catCnh) {
        Name = name;
        this.RG = RG;
        this.email = email;
        this.CPF = CPF;
        Phone = phone;
        this.CNHnumber = CNHnumber;
        this.catCnh = catCnh;
    }




    public int getCNHnumber() {
        return CNHnumber;
    }

    public void setCNHnumber(int CNHnumber) {
        this.CNHnumber = CNHnumber;
    }

    public String getCatCnh() {
        return catCnh;
    }

    public void setCatCnh(String catCnh) {
        this.catCnh = catCnh;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
}
