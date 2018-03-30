package com.trufle.lazarparent;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Weirdo on 21-02-2017.
 */

public class RecylerAdapter extends RecyclerView.Adapter<RecylerAdapter.MyViewHolder> {


    List<ChildDetails> childModel;
    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView abc;
        public MyViewHolder(View view) {
            super(view);
              abc = (TextView) view.findViewById(R.id.tv_age);

        }
    }


    public RecylerAdapter(List<ChildDetails> childModel, Context context) {
        this.childModel = childModel;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rview_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ChildDetails cm = childModel.get(position);
        holder.abc.setText(cm.getAge().toString());

    }

    @Override
    public int getItemCount() {
        return childModel.size();
    }
}