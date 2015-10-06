package com.example.quyhkse61160.hstsapp.Classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Man Huynh Khuong on 10/6/2015.
 */
public class ToDoTime {
    private String timeUse = "";
    private List<ToDoItem> items = new ArrayList<>();

    public ToDoTime() {
    }

    public String getTimeUse() {
        return timeUse;
    }

    public void setTimeUse(String timeUse) {
        this.timeUse = timeUse;
    }

    public List<ToDoItem> getItems() {
        return items;
    }

    public void setItems(List<ToDoItem> items) {
        this.items = items;
    }
}
