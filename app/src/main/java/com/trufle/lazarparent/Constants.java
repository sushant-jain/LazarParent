package com.trufle.lazarparent;

/**
 * Created by sushant on 29/3/18.
 */

public class Constants {
    public static class Parse {

        public static final String OBJECT_ID = "objectId";
        public static final String UPDATED_AT = "updatedAt";
        public static final String CREATED_AT = "createdAt";

        public static class Mother{
            public static final String TABLE_NAME="Mother";
            public static final String AADHAR_MOTHER="AdharMother";
            public static final String AADHAR_FATHER="AdharFather";
            public static final String NAME_MOTHER="NameMother";
            public static final String NAME_FATHER="NameFather";
            public static final String ADDRESS="Address";
            public static final String LOCATION_ID="LocationID";
            public static final String PHONE="Phone";
            public static final String EMAIL="Email";
        }

        public static class Child{
            public static final String TABLE_NAME="Child";
            public static final String MOTHER_ID="Mother";
            public static final String DOB="DOB";
            public static final String BCN="BCN";
        }

        public static class Transactions{
            public static final String PERSON="Person";
            public static final String VACCINE="Vaccine";
            public static final String DONE_DATE="DoneDate";
            public static final String DUE_DATE="DueDate";
        }

        public static class Pending{
            public static final String  PERSON="Person";
            public static final String VACCINE="Vaccine";
            public static final String DUE_DATE="DueDate";
        }


    }
    public static class SharedPreferences{
        public static final String MID="MID";
    }
}
