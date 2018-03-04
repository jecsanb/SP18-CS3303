package com.example.jb963962.loancalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        double payment_to_principalcalc;
        double payment_to_interestcalc;
        double total_interest = 0;

        principal_view = findViewById(R.id.loan_amount_display);
        payments_view = findViewById(R.id.payments_display);
        principal_view.setText(getString(R.string.loan_amount_str, values[0]));
        payments_view.setText(getString(R.string.number_of_payments, 12 * years));


        TableLayout table = findViewById(R.id.table);

        for (int i = 1; i <= 12 * years; ++i) {
            TableRow row = new TableRow(this);

            TextView month = new TextView(this);
            TextView monthly_payment = new TextView(this);
            TextView payment_to_principal = new TextView(this);
            TextView payment_to_interest = new TextView(this);


            TextView amnt_left = new TextView(this);

             payment_to_interestcalc = principal * (apr * .01) / 12;
             payment_to_principalcalc = monthly - payment_to_interestcalc;

            principal = principal - payment_to_principalcalc;
            total_interest += payment_to_interestcalc;

            month.setText(Integer.toString(i));
            monthly_payment.setText(String.format(moneyFormat, monthly));
            payment_to_principal.setText(String.format(moneyFormat, payment_to_principalcalc));
            payment_to_interest.setText(String.format(moneyFormat, payment_to_interestcalc));
            amnt_left.setText(String.format(moneyFormat, principal));

            month.setPadding(10, 0, 10, 0);
            monthly_payment.setPadding(10, 0, 10, 0);
            payment_to_interest.setPadding(10, 0, 10, 0);
            payment_to_principal.setPadding(10, 0, 10, 0);
            amnt_left.setPadding(10, 0, 10, 0);


            row.addView(month);
            row.addView(monthly_payment);
            row.addView(payment_to_principal);
            row.addView(payment_to_interest);
            row.addView(amnt_left);

            table.addView(row);
        }

        Intent result = new Intent();
        result.putExtra(MainActivity.CALCULATION_DATA,String.format(moneyFormat,total_interest));
        setResult(RESULT_OK,result);
    }
}
