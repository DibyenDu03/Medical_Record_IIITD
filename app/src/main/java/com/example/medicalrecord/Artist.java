package com.example.medicalrecord;

import android.widget.EditText;

import java.util.ArrayList;

public class Artist {

    ArrayList<String> med;
    public  Artist()
    {

    }

    public Artist(ArrayList<String> med1) {
        med=med1;
    }

    public ArrayList<String> getMed() {
        return med;
    }

}

