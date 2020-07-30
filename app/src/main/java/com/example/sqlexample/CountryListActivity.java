package com.example.sqlexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class CountryListActivity extends AppCompatActivity {
    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    final String[] from = new String[]{DatabaseHelper._ID,DatabaseHelper.SUBJECT,DatabaseHelper.DESC};
    final int[] to = new int[] {R.id.id,R.id.title,R.id.desc};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_emp_list);
        dbManager=  new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();
        listView = findViewById(R.id.list_item_view);
        listView.setEmptyView(findViewById(R.id.empty));
        adapter = new SimpleCursorAdapter(this,R.layout.activity_view_record,cursor,from,to,0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long viewId) {
                TextView idText = view.findViewById(R.id.id);
                TextView titleText = view.findViewById(R.id.title);
                TextView descText = view.findViewById(R.id.desc);
                String id = idText.getText().toString();
                String title = titleText.getText().toString();
                String desc = descText.getText().toString();
                Intent modify_intent = new Intent(getApplicationContext(),ModifyCountryActivity.class);
                modify_intent.putExtra("id",id);
                modify_intent.putExtra("title",title);
                modify_intent.putExtra("desc",desc);
                startActivity(modify_intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_record){
            Intent intent = new Intent(this,AddCountryActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}