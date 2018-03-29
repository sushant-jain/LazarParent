package com.trufle.lazarparent.ParseQueries;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.trufle.lazarparent.Constants;

/**
 * Created by sushant on 29/3/18.
 */

public class SignUp {
    public static final int SIGNUPCOMPLETED=1;
    public static final int SIGNUPFAILED=2;
    public static final int MID_NOT_MATCH=3;
    public interface OnSignUpCompletedListener{
        void onSignUpCompleted(int status);
    }

    public static void signUp(final String aadhar, final String mId, String pass, final OnSignUpCompletedListener onSignUpCompletedListener){
        ParseQuery<ParseObject> pq=ParseQuery.getQuery(Constants.Parse.Mother.TABLE_NAME);
        pq.getInBackground(mId, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(object.getString(Constants.Parse.Mother.AADHAR_MOTHER).equals(aadhar)){
                    ParseUser pu=new ParseUser();
                    pu.setUsername(aadhar);
                    pu.setPassword(mId);
                    pu.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e==null){
                                onSignUpCompletedListener.onSignUpCompleted(SIGNUPCOMPLETED);
                            }else{
                                onSignUpCompletedListener.onSignUpCompleted(SIGNUPFAILED);
                            }
                        }
                    });
                }else{
                    onSignUpCompletedListener.onSignUpCompleted(MID_NOT_MATCH);
                }
            }
        });
    }
    
}
