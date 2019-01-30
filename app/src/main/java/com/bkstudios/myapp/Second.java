package com.bkstudios.myapp;

import android.content.ContentValues;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.bkstudios.myapp.databinding.ActivitySecondBinding;

import java.util.ArrayList;

public class Second extends AppCompatActivity {
    ActivitySecondBinding secondBinding=null;
    ArrayList<String> data=new ArrayList<>();
    String reset;
    int id;
    String updateString = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       secondBinding= DataBindingUtil.setContentView(this,R.layout.activity_second);
       Bundle bundle=getIntent().getExtras();
       if (bundle != null) {
           updateString = bundle.getString("name");
           id = bundle.getInt("name1");

           //(Feeds) bundle.getSerializable("")

           secondBinding.commentbox.setText(updateString);
       }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.post:
                if(updateString==null) {
                    String post = secondBinding.commentbox.getText().toString();
                    ContentValues values = new ContentValues();
                    values.put(FB.post, post);
                    Database database = new Database(getApplicationContext());
                    database.getWritableDatabase().insert(FB.TABLE_NAME, null, values);
                    //                database.getWritableDatabase().delete(FB.TABLE_NAME,null,null);
                    finish();
                }
                else{
                    Database db=new Database(this);

                    String post = secondBinding.commentbox.getText().toString();
                    ContentValues values = new ContentValues();
                    values.put(FB.post, post);


                    String whereClause = FB.id + " = ?";
                    String[] whereArgs = new String[] {id + ""};
                    db.getWritableDatabase().update(FB.TABLE_NAME, values, whereClause, whereArgs);

                    finish();

                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}

