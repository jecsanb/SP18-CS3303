/*
 * file.java
 * Copyright (C) 2018 Jecsan Blanco <jblancolicano1@buffs.wtamu.edu>
 *
 * Distributed under terms of the MIT license.
 *
 *   @author Jecsan Blanco, Peterson Pham
 *   @version 1.0
 *   @since  02/18/2018
 *   This activity allows the user to enter more items. The items are returned back to the main activity
 *   for validation and storage whent he user hits the back button.
 *   */
package com.example.jb963962.computepricev2;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemEntryActivity extends AppCompatActivity {
    private final String ITEMS = "ITEMS";
    private EditText name_entry, price_entry, quantity_entry;
    private TextView number_of_items_text;
    private final String TOTAL_ITEMS = "TOTAL_ITEMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_entry);
        Intent intent = getIntent();

        //wire the textEdits
        name_entry = findViewById(R.id.item_name_entry);
        price_entry = findViewById(R.id.item_price_entry);
        quantity_entry = findViewById(R.id.item_qty_entry);
        number_of_items_text = findViewById(R.id.number_of_items_text);

        String total_items = getString(R.string.number_of_item_s,
                intent.getIntExtra(TOTAL_ITEMS, 0));

        number_of_items_text.setText(total_items);


    }

    @Override
    public void finish() {
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item(name_entry.getText().toString(),
                price_entry.getText().toString(),
                quantity_entry.getText().toString()));
        Intent intent = new Intent();
        intent.putExtra(ITEMS, items);
        setResult(RESULT_OK, intent);
        super.finish();
    }
}
