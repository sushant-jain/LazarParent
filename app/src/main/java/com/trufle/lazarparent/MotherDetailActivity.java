package com.trufle.lazarparent;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class MotherDetailActivity extends AppCompatActivity {
    TextView lastvcnname, lastvcndate, vcnname, vcndate;
    FloatingActionButton history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mother_detail);
        lastvcndate=findViewById(R.id.tv_lvcndate);
        lastvcnname=findViewById(R.id.tv_lvcnname);
        vcndate=findViewById(R.id.tv_vcndate);
        vcnname=findViewById(R.id.tv_vcnname);
        history=findViewById(R.id.fb_history);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MotherDetailActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

    }
}
