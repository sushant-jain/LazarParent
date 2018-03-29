package com.trufle.lazarparent;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.parse.ParseObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseObject po=new ParseObject(Constants.Parse.Mother.TABLE_NAME);
        po.put(Constants.Parse.Mother.NAME_MOTHER,"aa");
        po.saveInBackground();
    }
}
