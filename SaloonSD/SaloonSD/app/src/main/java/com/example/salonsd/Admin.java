package com.example.salonsd;

public class Admin {

    private String name;
    private String uname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String password;
    private String tel;
    private String email;

    public Admin(String name, String uname, String password, String tel, String email) {
        this.name = name;
        this.uname = uname;
        this.password = password;
        this.tel = tel;
        this.email = email;
    }
}
