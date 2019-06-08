package com.bassem.bassem.smartattendance.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bassem on 06/05/2019.
 */

public class Subject implements ModelInterface {

    private String email,date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Map<String, Object> toMap() {
        HashMap<String ,Object> result =new HashMap<>();
        result.put("email",email);


        return result;
    }
}
