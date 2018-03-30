package com.trufle.lazarparent;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseQuery;
import com.trufle.lazarparent.ParseQueries.SignUp;

public class Signup extends AppCompatActivity {
    EditText mid, aadhar, pass;
    AppCompatButton signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mid=findViewById(R.id.MID);
        aadhar=findViewById(R.id.Aadhar);
        pass=findViewById(R.id.pass);
        signup=findViewById(R.id.signup);
        mid.clearFocus();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp.signUp(aadhar.getText().toString(), mid.getText().toString(), pass.getText().toString(), new SignUp.OnSignUpCompletedListener() {
                    @Override
                    public void onSignUpCompleted(int status) {
                        if(status==SignUp.MID_NOT_MATCH){
                            Toast.makeText(Signup.this, "MId and aadhar don't match", Toast.LENGTH_SHORT).show();
                        }else if(status==SignUp.SIGNUPFAILED){
                            Toast.makeText(Signup.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Signup.this, "SignUp Succesfull", Toast.LENGTH_SHORT).show();
                            PreferenceManager.getDefaultSharedPreferences(Signup.this).edit().putString(Constants.SharedPreferences.MID,mid.getText().toString()).apply();
                        }
                    }
                });
            }
        });


    }

}
