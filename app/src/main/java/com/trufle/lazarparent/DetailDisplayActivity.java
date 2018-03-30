package com.trufle.lazarparent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.net.CacheRequest;
import java.util.ArrayList;
import java.util.Date;

public class DetailDisplayActivity extends AppCompatActivity {

    ArrayList<ChildDetails> childlist = null;
    CardView mother;
    TextView name, address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_display);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Family Details");
        address=findViewById(R.id.tv_adress);
        name=findViewById(R.id.tv_name);
        mother=findViewById(R.id.cv);
        mother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailDisplayActivity.this, MotherDetailActivity.class);
                startActivity(intent);
            }
        });

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        childlist=new ArrayList<ChildDetails>();
        childlist.add(new ChildDetails(new Date(1996,11,22),11,"ajaj"));
        childlist.add(new ChildDetails(new Date(1996,11,22),11,"ajaj"));
        childlist.add(new ChildDetails(new Date(1996,11,22),11,"ajaj"));


        RecyclerViewAdapter cAdapter = new RecyclerViewAdapter(childlist,DetailDisplayActivity.this);
        RecyclerView.LayoutManager cLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(cLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(DetailDisplayActivity.this, MotherDetailActivity.class);
                startActivity(intent);
           }
        }));


    }

}
