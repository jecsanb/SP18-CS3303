package com.example.jb963962.cardvalidator;
//Author: Jecsan Blanco
//copyright: jblancolicano1@buffs.wtamu.edu
//Front end for the AMXCCValidator, tells you if a AMX CC is valid or not.

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button validate;
    private EditText ccNumber;
    private TextView result;
    private boolean answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ccNumber = findViewById(R.id.ccNumber);
        result = findViewById(R.id.validator_reply);
        validate = findViewById(R.id.validate_button);
        validate.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v){

                  answer = AMXCCValidator.validate(ccNumber.getText().toString());
                  if(answer) {

                      result.setText("VALID");
                      result.setTextColor(Color.GREEN);
                      Toast.makeText(MainActivity.this, "Valid", Toast.LENGTH_SHORT).show();
                  }else {
                      result.setText("INVALID");
                      result.setTextColor(Color.RED);
                      Toast.makeText(MainActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}
