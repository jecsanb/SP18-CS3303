package com.example.jb963962.tablelayoutdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);

        String[] names = {"Row Tillman","Dave Dulap","Sim Travis","Sherry Zimmerman","Taylor Miller",
        "Robert Valentine", "Clay Rice", "Kerry Witt","Velman Berry","Tom Saxton"};
        TableLayout table = findViewById(R.id.table);
        for(String i : names){
            TableRow row = new TableRow(this);
            TextView tv1 = new TextView(this);
            TextView tv2 = new TextView(this);
            tv1.setText(i);
            tv2.setText(String.format("%d",((int)Math.random()*80)+10));
            tv1.setPadding(20,0,30,0);
            tv2.setPadding(0,0,20,0);
            row.addView(tv1);
            row.addView(tv2);
            table.addView(row);
        }
    }
}
