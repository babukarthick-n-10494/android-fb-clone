package com.bkstudios.myapp;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class DataThread extends AsyncTask<Void,String,ArrayList<Feeds>> {

    Context context=null;
    ArrayList<Feeds> data=null;
    ListAdapter adapter = null;

    public DataThread(Context context,ArrayList<Feeds> data, ListAdapter adapter){
        this.context=context;
        this.data=data;
        this.adapter = adapter;
        this.data.clear();
    }

    @Override
    protected ArrayList<Feeds> doInBackground(Void... voids) {

        String select = "SELECT * FROM " + FB.TABLE_NAME;
        Database database = new Database(context);


        Cursor cursor = database.getWritableDatabase().rawQuery(select,null);
        if (cursor.moveToFirst()) {
            do {
                Feeds post = new Feeds();
                post.setPost(cursor.getString(cursor.getColumnIndex(FB.post)));
                post.setAutoId(cursor.getInt(cursor.getColumnIndex(FB.id)));
                data.add(post);

            } while (cursor.moveToNext());

            return data;
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Feeds> feeds) {
        super.onPostExecute(feeds);
        adapter.notifyDataSetChanged();
    }
}

