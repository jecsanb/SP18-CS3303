package com.example.jb963962.loancalculator;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableActivity extends AppCompatActivity {
    private TextView principal_view,payments_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        // 1. Principal, 2.Years 4. Apr
        double values[] = getIntent().getDoubleArrayExtra(MainActivity.CALCULATION_DATA);

        principal_view = findViewById(R.id.loan_amount_display);
        payments_view = findViewById(R.id.payments_display);
        principal_view.setText(getString(R.string.loan_amount_str,values[0]));




        TableLayout table = findViewById(R.id.table);

    }
}
