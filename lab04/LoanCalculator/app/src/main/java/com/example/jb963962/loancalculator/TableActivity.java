package com.example.jb963962.loancalculator;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableActivity extends AppCompatActivity {
    private TextView principal_view, payments_view;
    private final String moneyFormat = "$%,.2f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        // 1. Principal, 2.Years 4. Apr
        double values[] = getIntent().getDoubleArrayExtra(MainActivity.CALCULATION_DATA);
        double principal = values[0];
        double apr = values[2];
        double monthly = values[1];
        double years = values[3];
        double col3calc;
        double col4calc;

        principal_view = findViewById(R.id.loan_amount_display);
        payments_view = findViewById(R.id.payments_display);
        principal_view.setText(getString(R.string.loan_amount_str, values[0]));
        payments_view.setText(getString(R.string.number_of_payments, 12 * years));


        TableLayout table = findViewById(R.id.table);

        for (int i = 1; i <= 12 * years; ++i) {
            TableRow row = new TableRow(this);

            TextView month = new TextView(this);
            TextView mpay = new TextView(this);
            TextView col3 = new TextView(this);
            TextView col4 = new TextView(this);
            TextView amt = new TextView(this);

            col3calc = principal * (apr * .01) / 12;
            col4calc = monthly - col3calc;
            principal = principal - col4calc;

            month.setText(Integer.toString(i));
            mpay.setText(String.format(moneyFormat, monthly));
            col3.setText(String.format(moneyFormat, col3calc));
            col4.setText(String.format(moneyFormat, col4calc));
            amt.setText(String.format(moneyFormat, principal));

            month.setPadding(10, 0, 10, 0);
            mpay.setPadding(10, 0, 10, 0);
            col4.setPadding(10, 0, 10, 0);
            col3.setPadding(10, 0, 10, 0);
            amt.setPadding(10, 0, 10, 0);


            row.addView(month);
            row.addView(mpay);
            row.addView(col3);
            row.addView(col4);
            row.addView(amt);

            table.addView(row);
        }

    }
}
