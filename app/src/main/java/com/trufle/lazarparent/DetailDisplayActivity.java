package com.trufle.lazarparent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DetailDisplayActivity extends AppCompatActivity {

    ArrayList<ChildDetails> childlist = new ArrayList<>();
   CardView mother;
    TextView name, address;
    ParseObject motherObj;

    private static final String TAG = "DetailDisplayActivity";
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
                intent.putExtra(MotherDetailActivity.INTENT_SEL,MotherDetailActivity.MOTHER_SEL);
                intent.putExtra(MotherDetailActivity.PERSON,motherObj.getObjectId());
                startActivity(intent);
            }
        });

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        ParseQuery<ParseObject> pq1=ParseQuery.getQuery(Constants.Parse.Mother.TABLE_NAME);
        CustomProgressDialog2.showCustomDialog(DetailDisplayActivity.this);
        Log.d(TAG, "onCreate: mid+"+Utils.readSharedPreferences(DetailDisplayActivity.this, Constants.SharedPreferences.MID));
        pq1.getInBackground(Utils.readSharedPreferences(DetailDisplayActivity.this, Constants.SharedPreferences.MID), new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                CustomProgressDialog2.dismissCustomDialog();
                if(e==null) {
                    motherObj=object;
                    address.setText(object.getString(Constants.Parse.Mother.ADDRESS));
                    name.setText(object.getString(Constants.Parse.Mother.NAME_MOTHER));
                    ParseQuery<ParseObject> pq=ParseQuery.getQuery(Constants.Parse.Child.TABLE_NAME);
                    Log.d(TAG, "onCreate: "+motherObj.getObjectId());
                    pq.whereEqualTo(Constants.Parse.Child.MOTHER_ID,motherObj);
//        pq.include(Constants.Parse.Child.MOTHER_ID);

                    CustomProgressDialog.showCustomDialog(DetailDisplayActivity.this);

                    pq.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            CustomProgressDialog.dismissCustomDialog();
                            if(e==null){
                                Log.d(TAG, "done: "+objects.size());
                                for(ParseObject po:objects){
                                    childlist.add(new ChildDetails(po));
                                }
                                RecyclerViewAdapter cAdapter = new RecyclerViewAdapter(childlist,DetailDisplayActivity.this);
                                RecyclerView.LayoutManager cLayoutManager = new GridLayoutManager(getApplicationContext(),2);
                                recyclerView.setLayoutManager(cLayoutManager);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                                recyclerView.setAdapter(cAdapter);

                            }
                        }
                    });

                }
            }
        });









        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(DetailDisplayActivity.this, MotherDetailActivity.class);
                intent.putExtra(MotherDetailActivity.INTENT_SEL,MotherDetailActivity.CHILD_SEL);
                intent.putExtra(MotherDetailActivity.PERSON,childlist.get(i).ChildObject.getObjectId());
                startActivity(intent);
           }
        }));


    }

}
