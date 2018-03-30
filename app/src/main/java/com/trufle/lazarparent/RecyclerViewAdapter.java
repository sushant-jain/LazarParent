package com.trufle.lazarparent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    List<ChildDetails> childModel;
    Context context;

    public RecyclerViewAdapter(List<ChildDetails> childModel, Context context) {
        this.childModel = childModel;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rview_item, parent, false);
        return new RecyclerViewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ChildDetails cm = childModel.get(position);
        holder.abc.setText(cm.Name);

    }

    @Override
    public int getItemCount() {
        return childModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView abc;
        public MyViewHolder(View view) {
            super(view);
            abc =  view.findViewById(R.id.tv_age);

        }
    }



}
