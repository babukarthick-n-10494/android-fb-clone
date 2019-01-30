package com.bkstudios.myapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity activity=null;
    ArrayList<Feeds> data=null;
    public ListAdapter(ArrayList<Feeds> data,Activity activity){
        this.data=data;
        this.activity=activity;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view =inflater.inflate(R.layout.addons,viewGroup,false);
        ListHolder holder=new ListHolder(view, activity);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ListHolder) viewHolder).setData(data.get(i), i);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
