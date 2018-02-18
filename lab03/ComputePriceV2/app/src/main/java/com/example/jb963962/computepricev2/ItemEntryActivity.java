package com.example.jb963962.computepricev2;

import android.content.Intent;
import android.content.res.Resources;
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
