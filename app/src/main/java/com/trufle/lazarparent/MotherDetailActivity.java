package com.trufle.lazarparent;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MotherDetailActivity extends AppCompatActivity {
    TextView lastvcnname, lastvcndate, vcnname, vcndate;
    FloatingActionButton history;

    TextView tvChild,tvBcNo,tvMid;
    public static final int MOTHER_SEL=1;
    public static final int CHILD_SEL=2;
    public static final String INTENT_SEL="IntentSel";
    public static final String PERSON="person";

    String[] vaccine;

    String[] duedate;

    String[] donedate;

    ArrayList<ParseObject> tAL;

    ParseObject person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mother_detail);
        lastvcndate=findViewById(R.id.tv_lvcndate);
        lastvcnname=findViewById(R.id.tv_lvcnname);
        vcndate=findViewById(R.id.tv_vcndate);
        vcnname=findViewById(R.id.tv_vcnname);
        history=findViewById(R.id.fb_history);
        tvChild=findViewById(R.id.tv_cid);
        tvBcNo=findViewById(R.id.tv_bcn);
        tvMid=findViewById(R.id.tv_mid);

        if(getIntent().getIntExtra(INTENT_SEL,200)==MOTHER_SEL){
            tvChild.setVisibility(View.INVISIBLE);
            tvBcNo.setVisibility(View.INVISIBLE);
            ParseQuery<ParseObject> pq=ParseQuery.getQuery(Constants.Parse.Mother.TABLE_NAME);
            CustomProgressDialog.showCustomDialog(MotherDetailActivity.this);
            pq.getInBackground(getIntent().getStringExtra(PERSON), new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    CustomProgressDialog.dismissCustomDialog();
                    if(e==null){
                        person=object;
                        tvMid.setText(object.getString(Constants.Parse.Mother.NAME_MOTHER));
                        afterP();
                    }
                }
            });
        }else{
            ParseQuery<ParseObject> pq=ParseQuery.getQuery(Constants.Parse.Child.TABLE_NAME);
            CustomProgressDialog.showCustomDialog(MotherDetailActivity.this);
            pq.getInBackground(getIntent().getStringExtra(PERSON), new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    CustomProgressDialog.dismissCustomDialog();
                    if(e==null){
                        person=object;
                        tvMid.setText(object.getString(Constants.Parse.Child.BCN));
                        afterP();
                    }
                }
            });
        }



        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MotherDetailActivity.this, HistoryActivity.class);
                intent.putExtra(PERSON,getIntent().getStringExtra(PERSON));
                intent.putExtra(HistoryActivity.T_v,vaccine);
                intent.putExtra(HistoryActivity.T_done,donedate);
                intent.putExtra(HistoryActivity.T_due,duedate);
                startActivity(intent);
            }
        });

    }

    void afterP(){
        final ParseQuery<ParseObject> transactionQuery=ParseQuery.getQuery(Constants.Parse.Transactions.TABLE_NAME);
        transactionQuery.whereEqualTo(Constants.Parse.Transactions.PERSON,person);
        transactionQuery.addDescendingOrder(Constants.Parse.Transactions.DONE_DATE);
        CustomProgressDialog.showCustomDialog(MotherDetailActivity.this);
        transactionQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                CustomProgressDialog.dismissCustomDialog();
                if(e==null) {
                    if(objects.size()>0) {
                        tAL = (ArrayList<ParseObject>) objects;
                        vaccine = new String[objects.size()];
                        duedate = new String[objects.size()];
                        donedate = new String[objects.size()];
                        for (int i = 0; i < objects.size(); i++) {
                            vaccine[i] = objects.get(i).getString(Constants.Parse.Transactions.VACCINE);
                            duedate[i] = new SimpleDateFormat("dd/MM/yyyy").format(objects.get(i).getDate(Constants.Parse.Transactions.DUE_DATE));
                            donedate[i] = new SimpleDateFormat("dd/MM/yyyy").format(objects.get(i).getDate(Constants.Parse.Transactions.DONE_DATE));
                        }
                        lastvcndate.setText(new SimpleDateFormat("dd/MM/yyyy").format(objects.get(0).getDate(Constants.Parse.Transactions.DONE_DATE)));
                        lastvcnname.setText(objects.get(0).getString(Constants.Parse.Transactions.VACCINE));

                        ParseQuery<ParseObject> pendingQuery = ParseQuery.getQuery(Constants.Parse.Pending.TABLE_NAME);
                        pendingQuery.whereEqualTo(Constants.Parse.Pending.PERSON, person);
                        CustomProgressDialog.showCustomDialog(MotherDetailActivity.this);

                        pendingQuery.getFirstInBackground(new GetCallback<ParseObject>() {
                                                              @Override
                                                              public void done(ParseObject object, ParseException e) {
                                                                  CustomProgressDialog.dismissCustomDialog();
                                                                  if (e == null) {
                                                                      vcndate.setText(new SimpleDateFormat("dd/MM/yyyy").format(object.getDate(Constants.Parse.Pending.DUE_DATE)));
                                                                      vcnname.setText(object.getString(Constants.Parse.Pending.VACCINE));
                                                                  } else {
                                                                      e.printStackTrace();
                                                                  }
                                                              }
                                                          }
                        );
                    }
                }else{
                    e.printStackTrace();
                }
            }


        });


    }
}
