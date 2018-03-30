package com.trufle.lazarparent;

import java.text.SimpleDateFormat;
import java.util.Date;

class ChildDetails {
    Date DOB;

    public ChildDetails(Date DOB,Integer age, String C_ID)
    {
        this.DOB=DOB;
        this.Age=age;
        this.C_ID=C_ID;
    }

    public Integer getAge() {
        return Age;
    }

    Integer Age;
    String C_ID;


    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public void setAge() {
        Integer year=DOB.getYear();
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy");
        Age=Integer.parseInt(sdf.format(new Date()))-year;
    }

    public String getC_ID() {
        return C_ID;
    }

    public void setC_ID(String c_ID) {
        C_ID = c_ID;
    }



}
