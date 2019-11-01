package com.example.medicalrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class fetch extends AppCompatActivity {

    DatabaseReference db;
    ListView list;
    List<Artist> artistList;

    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);
        Intent intent=getIntent();
        user = intent.getStringExtra("user");
        db = FirebaseDatabase.getInstance().getReference("disease").child(user);
        list = (ListView) findViewById(R.id.list);
        artistList = new ArrayList<>();
    }

    public void onStart() {
        super.onStart();

        db.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                artistList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Artist artist = dataSnapshot1.getValue(Artist.class);
                    artistList.add(artist);

                }
                news adapter = new news(fetch.this, artistList);
                list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
    }
}
