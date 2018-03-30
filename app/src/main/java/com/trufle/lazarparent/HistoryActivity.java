package com.trufle.lazarparent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    String[] vaccine;




    String[] duedate;





    String[] donedate;





    public static final String T_v="Transactions_v";
    public static final String T_due="Tdue";
    public static final String T_done="Tdone";

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        vaccine = getIntent().getStringArrayExtra(T_v);
        duedate = getIntent().getStringArrayExtra(T_due);
        donedate = getIntent().getStringArrayExtra(T_done);
        MyListAdapter adapter=new MyListAdapter(HistoryActivity.this, vaccine, duedate,donedate);
        list=(ListView)findViewById(R.id.lv_list);
        list.setAdapter(adapter);



    }
}
