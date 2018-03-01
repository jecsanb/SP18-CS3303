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

public class MainActivity extends AppCompatActivity {

    private Button calculate_button, reset_button, amortization_button;
    private EditText loan_amount_entry, apr_entry, loan_term_entry, loan_payment_entry;
    private double  monthly_payment, apr_rate, months;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //todo wire buttons

        //todo  wire  editText

        //todo wire the button listeners
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
    private double calculateMonthlyPayment(double principal , double rate, int months){
        double payment = 0.0;


        return payment;
    }


}


