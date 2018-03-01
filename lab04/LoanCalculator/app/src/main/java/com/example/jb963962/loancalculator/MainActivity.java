/*

Authors: Jecsan Blanco and Petterson Pham
Version: 02 / 28 / 2018

This program calculates the monthly payment given a loan amount, APR
(annual percentage rate), and number of years. This program also displays
 an amortization of the loan.
 */
package com.example.jb963962.loancalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button calculate_button, reset_button, amortization_button;
    private EditText loan_amount_entry, apr_entry, loan_term_entry, loan_payment_entry;
    private double  loan_amount,monthly_paymet, apr_rate, years;
    private TextView display_text;
    private EditText entry1,entry2,entry3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //todo wire buttons

        //todo  wire  editText

        //todo wire the button listeners

    }


    //test
    private boolean tryToGetFields() {
        //Tries to  pull data from the editText returns true if it was successful
        try{
            loan_amount = Double.parseDouble(entry1.getText().toString());
            apr_rate = Double.parseDouble(entry2.getText().toString());
            years =  Integer.parseInt(entry3.getText().toString());
        }catch(NumberFormatException e){
            Toast.makeText(this, "Invalid Input!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

    //todo write a way to pass the needed data to another activity that builds the scrollable table
    private void openAmortizationTable(){
    }

    private double calculateMonthlyPayment(double principal , double apr, int years){
        int months = 12;
        int monthsInYears = years*months;
        double rate = (apr/100)/months; // why is it under 12?
        return principal*(rate + ( rate /( Math.pow( 1 + rate,monthsInYears) -1 ) ) );

    }


}


