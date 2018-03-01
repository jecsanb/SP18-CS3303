/*

Authors: Jecsan Blanco and Petterson Pham
Version: 02 / 28 / 2018

This program calculates the monthly payment given a loan amount, APR
(annual percentage rate), and number of years. This program also displays
 an amortization of the loan.
 */
package com.example.jb963962.loancalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button calculate_button, reset_button, amortization_button;
    private EditText loan_amount_entry, apr_entry, loan_term_entry, loan_payment_entry;
    private double  monthly_payment, apr_rate, months;
    private TextView display_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //todo wire buttons

        //todo  wire  editText

        //todo wire the button listeners


        display_text = findViewById(R.id.display);
        calculateMonthlyPayment(2000.00,6,5);
    }


    //todo write a way to validate the fields and let the user know what is wrong.
    private boolean validateFields() {
        boolean answer = false;

        return answer;
    }

    //todo write a way to pass the needed data to another activity that builds the scrollable table
    private void openAmortizationTable(){

    }
    //todo write the formula for monthly payments
    private double calculateMonthlyPayment(double principal , double apr, int years){
        int months = years*12;
        double rate = (apr/100)/12; // why is it under 12?
        return principal*(rate + ( rate /( Math.pow( 1 + rate,months) -1 ) ) );

    }


}


