package com.example.jecsan.mystore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BillActivity extends AppCompatActivity {
    private final double TAX = 8.25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        Intent intent = getIntent();
        String[] menu = intent.getStringArrayExtra(StoreActivity.MENU);
        int[] item_qty = intent.getIntArrayExtra(StoreActivity.QTYS);

        TableLayout table = findViewById(R.id.bill_table);
        TextView subTotal_view = findViewById(R.id.subTotal);
        TextView tax_view = findViewById(R.id.tax);
        TextView total_view = findViewById(R.id.total);


        double subtotal = 0;
        double taxPercent = TAX / 100;
        double itemPrice;
        double total;


        TextView itemView, qtyView, totalView;
        String[] parts;
        TableRow row;
        for (int i = 0; i < menu.length; i++) {
            row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            parts = menu[i].split("\n");
            itemView = new TextView(this);
            itemView.setText(parts[0]);

            qtyView = new TextView(this);
            qtyView.setText(Integer.toString(item_qty[i]));

            itemPrice = Double.parseDouble(parts[1].substring(1));

            total = itemPrice * item_qty[i];
            totalView = new TextView(this);
            totalView.setText(String.format("$%.2f", total));

            row.addView(itemView);
            row.addView(qtyView);
            row.addView(totalView);

            table.addView(row, i);

            subtotal += total;
        }
        subTotal_view.setText(String.format("$%.2f", subtotal));

        total = subtotal + (taxPercent * subtotal);

        tax_view.setText(String.format("$%.2f", taxPercent * subtotal));

        total_view.setText(String.format("$%.2f", total));

    }
}
