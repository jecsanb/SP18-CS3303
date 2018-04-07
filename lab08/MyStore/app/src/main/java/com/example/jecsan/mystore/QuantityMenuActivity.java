package com.example.jecsan.mystore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class QuantityMenuActivity extends AppCompatActivity {
    private  int[] itemQtys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemQtys = new int[6];
        setContentView(R.layout.activity_quantity_menu);
        Intent intent = getIntent();

    }
}
