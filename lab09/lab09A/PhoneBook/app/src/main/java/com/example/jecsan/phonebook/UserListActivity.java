package com.example.jecsan.phonebook;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class UserListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        String [] projection = {MyContentProvider.COLUMN_FIRST_NAME, MyContentProvider.COLUMN_LAST_NAME,
                MyContentProvider.COLUMN_PHONE};
        Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI,
                projection,null,null);
        int count = cursor.getCount();
        cursor.moveToFirst();
        TableLayout tableLayout = new TableLayout(this);
        tableLayout.setVerticalScrollBarEnabled(true);
        TableRow row = new TableRow(this);
        TextView t0,t1,t2;
        t0 = new TextView(this);
        t0.setText("Firstname");
        t0.setTextColor(Color.RED);
        t0.setTypeface(null, Typeface.BOLD);
        t0.setPadding(20,20,20,20);
        row.addView(t0);
        t1 = new TextView(this);
        t1.setText("Lastname");
        t1.setTextColor(Color.RED);
        t1.setTypeface(null, Typeface.BOLD);
        t1.setPadding(20,20,20,20);
        row.addView(t1);
        t2 = new TextView(this);
        t2.setText("Phone Number");
        t2.setTextColor(Color.RED);
        t2.setTypeface(null, Typeface.BOLD);
        t2.setPadding(20,20,20,20);
        row.addView(t2);
        tableLayout.addView(row);
        for(int i = 0; i < count; i++){
            row = new TableRow(this);
            t0 = new TextView(this);
            t0.setText(cursor.getString(0));
            t0.setTextColor(Color.BLACK);
            t0.setPadding(20,20,20,20);
            row.addView(t0);
            t1 = new TextView(this);
            t1.setText(cursor.getString(1));
            t1.setTextColor(Color.BLACK);
            t1.setPadding(20,20,20,20);
            row.addView(t1);
            t2 = new TextView(this);
            String displayNumber = cursor.getString(2).substring(0,3) + "-" +
                                   cursor.getString(2).substring(3,6) + "-" +
                                   cursor.getString(2).substring(6,10);
            t2.setText(displayNumber);
            t2.setTextColor(Color.BLACK);
            t2.setPadding(20,20,20,20);
            row.addView(t2);
            tableLayout.addView(row);
            cursor.moveToNext();
        }
        setContentView(tableLayout);
    }

}
