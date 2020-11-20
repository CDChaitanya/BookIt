package com.example.bookit;

import java.util.ArrayList;

public class Buses {

    private ArrayList<String> time;

    public Buses(){

    }

    public Buses(ArrayList<String> time) {
        this.time = time;
    }

    public ArrayList<String> getTime() {
        return time;
    }

    public void setTime(ArrayList<String> time) {
        this.time = time;
    }
}
