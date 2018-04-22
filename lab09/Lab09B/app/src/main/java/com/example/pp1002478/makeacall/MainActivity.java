package com.example.pp1002478.makeacall;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRST_NAME = "firstName";
    private static final String COLUMN_LAST_NAME =  "lastName";
    private static final String COLUMN_PHONE = "phoneNumber";
    private static final String AUTHORITY = "com.example.jecsan.phonebook.MyContentProvider";
    private static final String TABLE_CONTACT = "contacts";


    EditText firstName, lastName;
    TextView showNumber;
    Button   find, makeCall;
    long     phone_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstName  = findViewById(R.id.firstName);
        lastName   = findViewById(R.id.lastName);
        showNumber = findViewById(R.id.show_number);
        find       = findViewById(R.id.find);
        find.setOnClickListener(this);
        makeCall   = findViewById(R.id.makecall);
        makeCall.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.find:
                findCaller();
                break;
            case R.id.makecall:
                call();
                break;

        }
    }

    private void findCaller() {
        Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_CONTACT);
        ContentResolver contentResolver = getContentResolver();
        String[] projection = {COLUMN_FIRST_NAME,COLUMN_LAST_NAME,COLUMN_PHONE};
        Cursor cursor = contentResolver
                .query(CONTENT_URI,projection,null,null,null);

        Log.i("testing",CONTENT_URI.toString());
        String s = "";
        if(!cursor.moveToFirst())
            s = "no result to display";
        else{
            do{
                Log.i("testing ", cursor.getString(0) +  " " + cursor.getString(1));
                Log.i("fname ", firstName.getText().toString() +  " " + lastName.getText().toString());
                if ( (firstName.getText().toString().equals(cursor.getString(0))) &&
                        (lastName.getText().toString().equals(cursor.getString(1)))  )  {
                    Log.i("testing if valid ", "I should be in.");
                    s = String.format("%-10s\n", cursor.getString(2));
                    showNumber.setText(s);
                    phone_number = cursor.getShort((2));
                }
            }while (cursor.moveToNext());
        }
        showNumber.setText(s);
    }

    private void call() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + showNumber.getText().toString()));
        startActivity(intent);
    }

}
