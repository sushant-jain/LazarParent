package com.trufle.lazarparent;

import android.net.ParseException;

import com.parse.ParseObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class ChildDetails {
    Date DOB;
    ParseObject ChildObject;

    public ChildDetails(Date DOB,Integer age, String C_ID)
    {
        this.DOB=DOB;
        this.Age=age;
        this.C_ID=C_ID;
        setAge();
    }

    public  ChildDetails(ParseObject ChilObj){
        ChildObject=ChilObj;
        DOB=ChilObj.getDate(Constants.Parse.Child.DOB);
        C_ID=ChilObj.getObjectId();
        Age=calculateAge(ChilObj.getDate(Constants.Parse.Child.DOB));

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


        public static int calculateAge(Date birthdate) {
            Calendar birth = Calendar.getInstance();
            birth.setTime(birthdate);
            Calendar today = Calendar.getInstance();

            int yearDifference = today.get(Calendar.YEAR)
                    - birth.get(Calendar.YEAR);

            if (today.get(Calendar.MONTH) < birth.get(Calendar.MONTH)) {
                yearDifference--;
            } else {
                if (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)
                        && today.get(Calendar.DAY_OF_MONTH) < birth
                        .get(Calendar.DAY_OF_MONTH)) {
                    yearDifference--;
                }

            }

            return yearDifference;
        }






}
