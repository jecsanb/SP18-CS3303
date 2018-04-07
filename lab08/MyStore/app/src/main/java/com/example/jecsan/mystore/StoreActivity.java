package com.example.jecsan.mystore;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class StoreActivity extends AppCompatActivity implements View.OnClickListener {
    private final int QTY_REQUEST = 1;
    private HashMap<Integer,Integer> item_qtys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int number_of_menu_items = 6;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        item_qtys = new HashMap<>(number_of_menu_items);
    }

    @Override
    public void onClick(View v) {
        openQuantityMenu(v.getId());

    }
    private  void openQuantityMenu(int item){

        Intent intent = new Intent(this,QuantityMenuActivity.class);
        intent.putExtra("item",item);
        startActivityForResult(intent,QTY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == RESULT_OK && requestCode == QTY_REQUEST){


        }else{
            Toast.makeText(this, "Oh no! Something went wrong.", Toast.LENGTH_SHORT).show();
        }
    }
}
