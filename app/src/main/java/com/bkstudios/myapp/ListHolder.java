package com.bkstudios.myapp;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ListHolder extends RecyclerView.ViewHolder {
    View view=null;
    Activity activity = null;

    public ListHolder(@NonNull View itemView, Activity activity) {
        super(itemView);
        view=itemView;
        this.activity = activity;
    }

    public void setData(final Feeds data, final int pos) {
        TextView tv=(TextView) view.findViewById(R.id.tv);
        View deleteIcon = view.findViewById(R.id.delete);
        View uploadIcon=view.findViewById(R.id.edit);
        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) activity).delete(pos, data);

                Database db=new Database(activity);

                String whereClause = FB.id + " = ?";
                String[] whereArgs = new String[]{data.getAutoId() + ""};

                db.getWritableDatabase().delete(FB.TABLE_NAME, whereClause, whereArgs);


            }
        });
        uploadIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity) activity).update(data);

            }
        });
        tv.setText(data.getPost());
    }
}
