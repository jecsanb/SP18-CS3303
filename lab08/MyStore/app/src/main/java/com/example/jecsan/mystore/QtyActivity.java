package com.example.jecsan.mystore;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class QtyActivity extends AppCompatActivity {
    private EditText qtyEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qty);

        Intent intent = getIntent();

        int clicked = intent.getIntExtra("clicked", -1);
        String[] menu = intent.getStringArrayExtra("menu");
        int[] item_qty = intent.getIntArrayExtra("qtys");
        StringBuilder sb = new StringBuilder("Your order so far \n");
        for(int i = 0; i < menu.length; i++){
            sb.append(menu[i].split("\n")[0] + ": " + item_qty[i] + "\n");
        }
        TextView qtyMsg = findViewById(R.id.items_so_far);
        qtyMsg.setText(sb.toString());


        qtyEntry = findViewById(R.id.qty_entry);

        TextView itemName = findViewById(R.id.item_name);
        itemName.setText(menu[clicked]);


    }
    @Override
    public void onBackPressed(){
        String qty = qtyEntry.getText().toString();
        if(qty.equals("")){
            Toast.makeText(this, "Enter something!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent returnIntent = getIntent();
        returnIntent.putExtra("result", qty.equals("") ? "0" : qty);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }


}
