package com.example.sqlexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyCountryActivity extends AppCompatActivity {
EditText titleText,descText;
Button updateBtn, deleteBtn;
long _id;
DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_record);
        setTitle("Modify Record");
        dbManager = new DBManager(this);
        dbManager.open();
        titleText = findViewById(R.id.subject_edittext);
        descText = findViewById(R.id.description_edittext);
        updateBtn = findViewById(R.id.btn_update);
        deleteBtn = findViewById(R.id.btn_delete);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");
        _id= Long.parseLong(id);
        titleText.setText(name);
        descText.setText(desc);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title =titleText.getText().toString();
                String desc = descText.getText().toString();
                dbManager.update(_id,title,desc);
                returnHome();
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbManager.delete(_id);
                returnHome();
            }
        });
    }
    public void returnHome(){
        Intent intent = new Intent(getApplicationContext(),CountryListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}