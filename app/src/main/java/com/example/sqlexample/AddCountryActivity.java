package com.example.sqlexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCountryActivity extends AppCompatActivity {

 Button addToDoBtn;
 EditText subjectEditText, descEditText;
 DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        setTitle("Add Record");
        subjectEditText = findViewById(R.id.subject_edittext);
        descEditText = findViewById(R.id.description_edittext);
        addToDoBtn = findViewById(R.id.add_record);
        dbManager = new DBManager(this);
        dbManager.open();
        addToDoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.add_record:
                        final String name = subjectEditText.getText().toString();
                        final String desc = descEditText.getText().toString();
                        dbManager.insert(name,desc);
                        Intent main = new Intent(AddCountryActivity.this,CountryListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(main);
                        break;
                }
            }
        });

    }
}