package com.example.marysia.todolist;

import android.app.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends //Activity{
    AppCompatActivity {

    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;
    private ListDb db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_main);

        db=new ListDb();
        lvItems = (ListView) findViewById(R.id.lvItems);
        ArrayList<String> items = db.getItems();
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }
    public void setupListViewListener(){
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, int position, long id) {
                db.removeItem(position);
                Toast.makeText(MainActivity.this, "Task deleted!", Toast.LENGTH_SHORT).show();
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
         lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View item, int position, long id) {
                String address = db.getItemById(position);
                if(address.startsWith("WWW.") || address.startsWith("www.")) {
                    if (!address.startsWith("http://") && !address.startsWith("https://"))
                        address = "http://" + address;

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(address));
                    startActivity(browserIntent);
                }
                else{
                    Toast.makeText(MainActivity.this, "This task is not a link!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void onAddItem(View view){
        EditText newTask = (EditText) findViewById(R.id.newTask);
        String taskText = newTask.getText().toString();
        if (taskText == taskText.toUpperCase())
        {
            itemsAdapter.add(taskText);
            newTask.setText("");
            Snackbar.make(findViewById(R.id.main_layout_id), "Dodano zadanie: " , Snackbar.LENGTH_LONG).show();;
        }
        else
            {
                Toast.makeText(MainActivity.this, "Upper Case please!", Toast.LENGTH_SHORT).show();
            }
    }
}


