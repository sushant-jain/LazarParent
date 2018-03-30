package com.trufle.lazarparent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class HistoryActivity extends AppCompatActivity {

    String[] vaccine ={
            "Hepatitis","Polio",
            "Cancer","Measles"
    };

    String[] duedate ={
            "22/11/1196","22/11/1196","22/11/1196","22/11/1196"
    };

    String[] donedate ={
            "22/11/1196","22/11/1196","22/11/1196","22/11/1196"
    };

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        MyListAdapter adapter=new MyListAdapter(this, vaccine, duedate,donedate);
        list=(ListView)findViewById(R.id.lv_list);
        list.setAdapter(adapter);
    }
}
