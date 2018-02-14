package com.example.jb963962.computepricev2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private  Button calculate;
    private  EditText price_entry,quantity_entry,tax_entry,total_display;

    private  Double price,tax_rate,total;
    private  Integer qty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        price = tax_rate = total = 0.0;
        qty  = 0;
        price_entry = findViewById(R.id.item_price_entry);
        quantity_entry = findViewById(R.id.item_qty_entry);
        tax_entry= findViewById(R.id.item_tax_entry);

        total_display= findViewById(R.id.item_total_entry);
        total_display.setKeyListener(null);

        calculate = findViewById(R.id.compute_button);
        calculate.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v){
                calculate();
            }
        });
    }
    private void calculate(){
        try {
            price = Double.parseDouble(price_entry.getText().toString());
            qty = Integer.parseInt(quantity_entry.getText().toString());
            tax_rate = Double.parseDouble(tax_entry.getText().toString());
        }catch(NumberFormatException e) {
            invalidInputToast();
            return;
        }

        total = (price*qty) + (price*qty*tax_rate)/100;
        total_display.setText(String.format("$%.2f",total));

    }
    private void invalidInputToast(){
        Toast toast = Toast.makeText(this,"Invalid Input!",Toast.LENGTH_SHORT) ;
        TextView v = toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.RED);
        toast.show();
    }
}
