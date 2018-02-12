package com.example.jb963962.computepricev2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        price_entry = findViewById(R.id.price_entry_field);
        quantity_entry = findViewById(R.id.quantity_entry_field);
        tax_entry= findViewById(R.id.tax_entry_field);

        total_display= findViewById(R.id.total_display_field);
        total_display.setKeyListener(null);

        price_entry.setText("0.0");
        tax_entry.setText("0.0");
        total_display.setText("0");
        quantity_entry.setText("0");

        calculate = findViewById(R.id.compute_button);
        calculate.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v){
                Toast.makeText(MainActivity.this, "Works!", Toast.LENGTH_SHORT).show();
                price = Double.parseDouble(price_entry.getText().toString());
                qty = Integer.parseInt(quantity_entry.getText().toString());
                tax_rate = Double.parseDouble(tax_entry.getText().toString());

                total = (price*qty) + (price*qty*tax_rate)/100;
                total_display.setText(String.format("%.2f",total));
            }
        });
    }
}
