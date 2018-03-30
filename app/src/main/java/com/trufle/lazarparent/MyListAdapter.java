package com.trufle.lazarparent;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] vaccine;
    private final String[] duedate;
    private final String[] donedate;


    public MyListAdapter(Activity context, String[] vaccine,String[] duedate,String[] donedate) {
        super(context, R.layout.custom_list_view, vaccine);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.vaccine=vaccine;
        this.duedate=duedate;
        this.donedate=donedate;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.custom_list_view, null,true);

        TextView tv_vacine = (TextView) rowView.findViewById(R.id.tv_vacine);
        TextView tv_duedate = (TextView) rowView.findViewById(R.id.tv_duedate);
        TextView tv_donedate = (TextView) rowView.findViewById(R.id.tv_donedate);

        tv_vacine.setText(vaccine[position]);
        tv_duedate.setText(duedate[position]);
        tv_donedate.setText(donedate[position]);
        return rowView;

    };
}
