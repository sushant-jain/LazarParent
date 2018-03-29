package com.trufle.lazarparent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText mid, pass;
    AppCompatButton login;
    TextView link_signup;
    LinearLayout mainlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mid=findViewById(R.id.MID);
        findViewById(R.id.mainlayout).requestFocus();
        pass=findViewById(R.id.pass);
        login=findViewById(R.id.login);
        link_signup=findViewById(R.id.link_signup);
        mid.setActivated(false);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"lalala",Toast.LENGTH_LONG).show();
            }
        });

        link_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "Random Random", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Signup.class);

                startActivity(intent);
                finish();
            }
        });
    }
}
