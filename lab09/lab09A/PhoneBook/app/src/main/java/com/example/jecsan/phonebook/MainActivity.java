package com.example.jecsan.phonebook;

import android.content.Intent;
import android.renderscript.Script;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        for (EditText field : fields) {
            if (field.getText().toString().equals("")) {
                field.setError("Field Required!");
                return  true;
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.add_button:
                if(checkForEmptyFields()){
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
        Toast.makeText(this, "Contact Deleted!", Toast.LENGTH_SHORT).show();
    }

    private Contact createContact() {
        try {
            return new Contact(fname_entry.getText().toString(),
                    lnameEntry.getText().toString(),
                    phoneEntry.getText().toString()
            );

        } catch (Exception e) {

            return null;
        }
    }

    private void addContactToDatabase(Contact contact) {
        //pull data from fields and added it to data base
        Toast.makeText(this, "Contact Added!", Toast.LENGTH_SHORT).show();
    }
}
