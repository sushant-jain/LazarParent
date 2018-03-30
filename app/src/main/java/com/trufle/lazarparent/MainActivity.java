package com.trufle.lazarparent;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
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
                CustomProgressDialog.showCustomDialog(MainActivity.this);
                ParseUser.logInInBackground(mid.getText().toString(), pass.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(e==null){
                            ParseQuery<ParseObject> pq=ParseQuery.getQuery(Constants.Parse.Mother.TABLE_NAME);
                            pq.whereEqualTo(Constants.Parse.Mother.AADHAR_MOTHER,mid.getText().toString());
                            pq.getFirstInBackground(new GetCallback<ParseObject>() {
                                @Override
                                public void done(ParseObject object, ParseException e) {
                                    CustomProgressDialog.dismissCustomDialog();
                                    if(e==null){
                                        Toast.makeText(MainActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                                        PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit().putString(Constants.SharedPreferences.MID,object.getString(Constants.Parse.OBJECT_ID)).apply();
                                    }else{
                                        e.printStackTrace();
                                        Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }else{
                            ParseQuery<ParseObject> pq=ParseQuery.getQuery(Constants.Parse.Mother.TABLE_NAME);
                            Log.d(TAG, "done: "+mid.getText().toString());
                            pq.getInBackground(mid.getText().toString(), new GetCallback<ParseObject>() {
                                @Override
                                public void done(ParseObject object, ParseException e) {
                                    if(e==null){
                                        ParseUser.logInInBackground(object.getString(Constants.Parse.Mother.AADHAR_MOTHER), pass.getText().toString(), new LogInCallback() {
                                            @Override
                                            public void done(ParseUser user, ParseException e) {
                                                CustomProgressDialog.dismissCustomDialog();
                                                if(e==null){
                                                    Toast.makeText(MainActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                                                    PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit().putString(Constants.SharedPreferences.MID,mid.getText().toString()).apply();
                                                }else{
                                                    e.printStackTrace();
                                                    Toast.makeText(MainActivity.this, "Error Logging In", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }else {
                                        CustomProgressDialog.dismissCustomDialog();
                                        e.printStackTrace();
                                        Toast.makeText(MainActivity.this, "Error1", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    }
                });
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
