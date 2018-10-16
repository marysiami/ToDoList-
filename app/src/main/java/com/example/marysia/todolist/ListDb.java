package com.example.marysia.todolist;
import android.app.Application;
import java.util.ArrayList;


public class ListDb extends Application {

    private ArrayList<String> items;


    public ListDb(){
        items = new ArrayList<String>();
        addItem("First task");
        addItem("Second task");
    }

    public void addItem(String item){
        this.items.add(item);
    }

    public void removeItem(int pos) {
        this.items.remove(pos);
    }

    public ArrayList <String> getItems(){
        return this.items;
    }
    public String getItemById(int pos){
        return this.items.get(pos);
    }
}

