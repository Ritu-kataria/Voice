package com.example.voicebasedprescription;

import android.widget.CheckBox;

 public class Helper {
    String name;
    String email;
     String phone;
     String pass;
    CheckBox M;
    CheckBox F;

    public Helper() {
    }

    public Helper(String name, String email, String phone, String pass) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.pass = pass;
        this.M = M;
        this.F = F;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public  String getPhone() {
        return phone;
    }

    public  String getPass() {
        return pass;
    }

    public CheckBox getM() {
        return M;
    }

    public CheckBox getF() {
        return F;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setM(CheckBox m) {
        M = m;
    }

    public void setF(CheckBox f) {
        F = f;
    }
}
