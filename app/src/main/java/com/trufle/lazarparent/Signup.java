package com.trufle.lazarparent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
                Toast.makeText(Signup.this, "Signup Bencho", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Signup.this, DetailDisplayActivity.class);

                startActivity(intent);
                finish();
            }
        });


    }

}
