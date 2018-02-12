package com.example.jecsan.geoquizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView question_label;
    Button no, yes, next, previous;
    Question[] questionBank;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i = 0;
        questionBank = new Question[5];
        Log.v("Variable length", ("=" + questionBank.length));

        questionBank[0] = new Question(
                "Mexico City is the capital of Mexico.", true);
        questionBank[1] = new Question(
                "Toronto is the capital of Canada.", false);
        questionBank[2] = new Question(
                "London is the capital of United Kingdom.", true);
        questionBank[3] = new Question(
                "Milan is the capital of Italy.", false);
        questionBank[4] = new Question(
                "Jerusalem is the capital of Israel.", true);

        question_label = findViewById(R.id.question_text);
        question_label.setText(questionBank[i].getStatement());

        //wire buttons and set listener
        yes = findViewById(R.id.yes_button);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verify(true);
            }
        });
        no = findViewById(R.id.no_button);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify(false);

            }
        });

        next = findViewById(R.id.next_button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = (i + 1) % questionBank.length;
//                Toast.makeText(MainActivity.this,
//                        "i =" + i,Toast.LENGTH_SHORT).show();
                question_label.setText(questionBank[i].getStatement());

            }
        });

        previous = findViewById(R.id.prev_button);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = ((i - 1) + questionBank.length) % questionBank.length;
//                Toast.makeText(MainActivity.this,
//                        "i =" + i,Toast.LENGTH_SHORT).show();
                question_label.setText(questionBank[i].getStatement());
            }
        });


    }

    private void verify(boolean b) {
        if (questionBank[i].getAsnwer() == b) {
            Toast.makeText(MainActivity.this,
                    "Correct answer", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this,
                    "Incorrect answer", Toast.LENGTH_SHORT).show();
        }
    }
}
