package com.example.jecsan.phonebook;
/**
 * Authors:  Peterson Pham  and Jecsan Blanco
 * Work done  50/50 for whole project.
 * Version: 04/22/19
 * Allows a user to add contacts to a database.
 */

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.renderscript.Script;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button addButton, deleteButton, showAllButton, clearFieldButton;
    private EditText fname_entry, lnameEntry, phoneEntry;
    private EditText fields[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(this);

        deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(this);

        showAllButton = findViewById(R.id.showAll_button);
        showAllButton.setOnClickListener(this);

        clearFieldButton = findViewById(R.id.clear_button);
        clearFieldButton.setOnClickListener(this);

        fname_entry = findViewById(R.id.first_name_entry);
        lnameEntry = findViewById(R.id.last_name_entry);
        phoneEntry = findViewById(R.id.phone_number_entry);

        fields = new EditText[]{fname_entry, lnameEntry, phoneEntry};

    }


    private void launchUserListActivity() {
        startActivity(new Intent(this, UserListActivity.class));
    }

    private boolean checkForEmptyFields() {
        EditText[] fields = {fname_entry, lnameEntry, phoneEntry};

        if (phoneEntry.getText().toString().length() != 10) {
            phoneEntry.setError("Invalid Number! (10 numbers only)");
            return false;
        }

        for (EditText field : fields) {
            if (field.getText().toString().equals("")) {
                field.setError("Field Required!");
                return false;
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.add_button:
                if (checkForEmptyFields()) {
                    Log.i("ADDING", "TESTING ADD TO DATABASE.");
                    addContactToDatabase(createContact());
                }
                break;
            case R.id.delete_button:
                deleteContactFromDatabase(createContact());
                break;
            case R.id.showAll_button:
                launchUserListActivity();
                break;
            case R.id.clear_button:
                clearAllFields();
                break;
        }
    }


    private void clearAllFields() {
        for (EditText field : fields) {
            field.setText("");
        }
    }

    private void deleteContactFromDatabase(Contact contact) {
        String selection = "firstName = \"" + fname_entry.getText().toString() + "\"" + " AND " +
                "lastName = \"" + lnameEntry.getText().toString() + "\"";
        int result =
                getContentResolver().delete(MyContentProvider.CONTENT_URI, selection, null);
        if (result > 0) {
            Toast.makeText(this, "Contact Deleted!", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "No Match.", Toast.LENGTH_SHORT).show();
    }

    private Contact createContact() {
        try {
            Log.i("ADDING", "IN createContact().");
            return new Contact(fname_entry.getText().toString(),
                    lnameEntry.getText().toString(),
                    phoneEntry.getText().toString()
            );

        } catch (Exception e) {

            return null;
        }

    }

    private void addContactToDatabase(Contact contact) {
        //pull data from fields and added it to data
        Log.i("TAGS", "THE TAGS OF CONTACT IS: " + contact.getfName() + " " + contact.getlName() + " " + contact.getPhoneNumber());

        ContentValues values = new ContentValues();
        values.put(MyContentProvider.COLUMN_FIRST_NAME, contact.getfName());
        values.put(MyContentProvider.COLUMN_LAST_NAME, contact.getlName());
        values.put(MyContentProvider.COLUMN_PHONE, contact.getPhoneNumber());
        Uri uri = getContentResolver().insert(MyContentProvider.CONTENT_URI, values);
        Toast.makeText(this, "Contact Added!", Toast.LENGTH_SHORT).show();
        fname_entry.setText("");
        lnameEntry.setText("");
        phoneEntry.setText("");
    }
}
