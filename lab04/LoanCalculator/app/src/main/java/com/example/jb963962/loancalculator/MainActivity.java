/*

Authors: Jecsan Blanco and Petterson Pham
Version: 02 / 28 / 2018

This program calculates the monthly payment given a loan amount, APR
(annual percentage rate), and number of years. This program also displays
 an amortization of the loan and amount paid in interest.
 */
package com.example.jb963962.loancalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int RETURN_DATA_REQUEST = 0 ;
    private Button calculate_button, reset_button, amortization_button;
    private EditText loan_amount_entry, apr_entry, loan_term_entry, loan_payment_display;
    private TextView total_interest_display;
    private double principal, monthly_payment, apr_rate, years;
    public static final String CALCULATION_DATA = "DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //id's attached to editTexts
        loan_amount_entry = findViewById(R.id.loan_entry);
        apr_entry = findViewById(R.id.apr_entry);
        loan_term_entry = findViewById(R.id.term_entry);
        loan_payment_display = findViewById(R.id.payment_entry);
        total_interest_display = findViewById(R.id.total_interest);


        //id's attached to buttons
        calculate_button = findViewById(R.id.calculateButton);
        reset_button = findViewById(R.id.resetButton);
        amortization_button = findViewById(R.id.tableButton);

        reset_button.setEnabled(false);
        amortization_button.setEnabled(false);

        //listeners for the buttons.

        calculate_button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (tryToGetFields()) {
                    reset_button.setEnabled(true);
                    amortization_button.setEnabled(true);
                    monthly_payment = calculateMonthlyPayment(principal, apr_rate, years);
                    formatFields();
                }
            }
        });
        //buttons listeners
        reset_button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                resetAllFields();
                reset_button.setEnabled(false);
                amortization_button.setEnabled(false);
            }
        });
        amortization_button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                openAmortizationTable();
            }
        });

    }


    private boolean tryToGetFields() {
        //Tries to  pull data from the editText returns true if it was successful
        try {
            principal = Double.parseDouble(loan_amount_entry.getText().toString());
            apr_rate = Double.parseDouble(apr_entry.getText().toString());
            years = Integer.parseInt(loan_term_entry.getText().toString());
            if (years * apr_rate * years * principal <= 0)
                throw new NumberFormatException("Zero or negative numbers given");
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid Input!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

    private void openAmortizationTable() {
        double fieldNumbers[] = {principal, monthly_payment, apr_rate, years};
        Intent intent = new Intent(this, TableActivity.class);
        intent.putExtra(CALCULATION_DATA, fieldNumbers);
        startActivityForResult(intent,RETURN_DATA_REQUEST); }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == RETURN_DATA_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                total_interest_display.setText(getString(R.string.interest_paid,data.getStringExtra(CALCULATION_DATA)));
            }

        }
    }

    private static double calculateMonthlyPayment(double principal, double apr_rate, double years) {
        int months = 12;
        double monthsInYears = years * months;
        double rate = (apr_rate / 100) / months; // why is it under 12?
        return (principal * (rate + (rate / (Math.pow(1 + rate, monthsInYears) - 1))));

    }
    //sets up the files to have the write money and percent formatting
    private void formatFields() {
        EditText fields[] = {loan_amount_entry, loan_payment_display, apr_entry};
        Double fieldNumbers[] = {principal, monthly_payment, apr_rate};
        int i = 0;
        while (i < fields.length - 1) {
            fields[i].setText(getString(R.string.money, fieldNumbers[i]));
            i++;
        }
        fields[i].setText(String.format("%.2f", fieldNumbers[i]) + "%");
    }

    private void resetAllFields() {
        EditText fields[] = {loan_amount_entry, loan_term_entry, loan_payment_display, apr_entry};
        for (EditText e : fields) {
            e.setText("");
        }
    }


}


