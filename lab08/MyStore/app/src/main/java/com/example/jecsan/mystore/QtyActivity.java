package com.example.jecsan.mystore;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class QtyActivity extends AppCompatActivity {
    private EditText qtyEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qty);

        Intent intent = getIntent();
        // collect data
        int clicked = intent.getIntExtra(StoreActivity.CLICKED, -1);
        String[] menu = intent.getStringArrayExtra(StoreActivity.MENU);
        int[] item_qty = intent.getIntArrayExtra(StoreActivity.QTYS);


        // set the name of the item clicked
        TextView item = findViewById(R.id.item_name);
        item.setText(menu[clicked].split("\n")[0]);

        // items so far
        TableLayout table = findViewById(R.id.qty_table);
        TextView text;
        for (int i = 0; i < menu.length; i++) {
            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            text = new TextView(this);
            text.setText(menu[i].split("\n")[0]);
            row.addView(text);
            text = new TextView(this);
            text.setText(Integer.toString(item_qty[i]));
            row.addView(text);
            table.addView(row, i);
        }
        qtyEntry = findViewById(R.id.qty_entry);

    }

    @Override
    public void onBackPressed() {
        String qty = qtyEntry.getText().toString();
        if (qty.equals("")) {
            Toast.makeText(this, "Enter something!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent returnIntent = getIntent();
        returnIntent.putExtra("result", qty.equals("") ? "0" : qty);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();

    }


}
