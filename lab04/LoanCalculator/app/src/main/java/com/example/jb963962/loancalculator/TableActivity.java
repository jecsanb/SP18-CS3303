package com.example.jb963962.loancalculator;
/*
    Generates a scrollable table with all payments that need to be made.
    The table generated is an  amortization table.
 */

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
        //values: 0.principal, 1.monthly_payment, 2.apr_rate, 3.years
        double values[] = getIntent().getDoubleArrayExtra(MainActivity.CALCULATION_DATA);
        double principal = values[0];
        double monthly = values[1];
        double apr = values[2];
        double years = values[3];

        double payment_principalcalc,payment_interestcalc;
        double total_interest = 0;

        final String  MONTHLY_PAYMENT = String.format(moneyFormat, monthly);

        principal_view = findViewById(R.id.loan_amount_display);
        payments_view = findViewById(R.id.payments_display);
        principal_view.setText(getString(R.string.loan_amount_str, values[0]));
        payments_view.setText(getString(R.string.number_of_payments, 12 * years));


        TableLayout table = findViewById(R.id.table);

        TextView month,monthly_payment,payment_principal,payment_interest,amnt_left;

        for (int i = 1; i <= 12 * years; ++i) {
            TableRow row = new TableRow(this);

             month = new TextView(this);
             monthly_payment = new TextView(this);
             payment_principal = new TextView(this);
             payment_interest = new TextView(this);
             amnt_left = new TextView(this);

             payment_interestcalc = principal * (apr * .01) / 12;
             payment_principalcalc = monthly - payment_interestcalc;

            principal = principal - payment_principalcalc;
            total_interest += payment_interestcalc;


            TextView tviews[] = { month,monthly_payment,payment_principal,payment_interest,amnt_left};
            String tvalues[] = {Integer.toString(i),MONTHLY_PAYMENT,String.format(moneyFormat, payment_principalcalc),
                String.format(moneyFormat, payment_interestcalc),String.format(moneyFormat, principal)};
            //adds the views to the rows and the data to the views.
            for(int view = 0; view < tviews.length; view++) {
                tviews[view].setText(tvalues[view]);
                tviews[view].setPadding(10, 0, 10, 0);
                row.addView(tviews[view]);
            }
            table.addView(row);
        }

        Intent result = new Intent();
        result.putExtra(MainActivity.CALCULATION_DATA,String.format(moneyFormat,total_interest));
        setResult(RESULT_OK,result);
    }
}
