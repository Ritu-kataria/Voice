package com.example.voicebasedprescription;

import android.widget.CheckBox;

 public class Helper {
    String Name;
    String Email;
     String Phone;
     String Password;
    CheckBox M;
    CheckBox F;

    public Helper() {
    }

     public Helper(String name, String email, String phone, String password) {
         Name = name;
         Email = email;
         Phone = phone;
         Password = password;
     }

     public String getName() {
         return Name;
     }

     public void setName(String name) {
         Name = name;
     }

     public String getEmail() {
         return Email;
     }

     public void setEmail(String email) {
         Email = email;
     }

     public String getPhone() {
         return Phone;
     }

     public void setPhone(String phone) {
         Phone = phone;
     }

     public String getPassword() {
         return Password;
     }

     public void setPassword(String password) {
         Password = password;
     }

     public CheckBox getM() {
         return M;
     }

     public void setM(CheckBox m) {
         M = m;
     }

     public CheckBox getF() {
         return F;
     }

     public void setF(CheckBox f) {
         F = f;
     }
 }
