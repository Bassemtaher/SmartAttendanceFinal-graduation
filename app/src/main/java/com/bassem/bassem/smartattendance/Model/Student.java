package com.bassem.bassem.smartattendance.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bassem on 16/04/2019.
 */

public class Student implements ModelInterface{
    private String email,password,name,phone,type,macAddress;

    public Student() {

    }

    public Student(String email, String password, String name, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public String getType() {return type;}

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public Map<String, Object> toMap() {
        HashMap<String ,Object> result =new HashMap<>();


        result.put("email",email);
        result.put("password",password);
        result.put("name",name);
        result.put("phone",phone);
        result.put("type",type);
        result.put("macAddress",macAddress);

        return result;
    }
}
