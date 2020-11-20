package com.example.bookit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private ListView lv;
    private TextView source;
   // private TextView destination;
    private TextView date;
    private ImageView arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lv = findViewById(R.id.firestore_list);
        source = findViewById(R.id.source);
        //destination = findViewById(R.id.destination);
        date = findViewById(R.id.date);
        arrow = findViewById(R.id.back_arrow);
        String txt_source = getIntent().getStringExtra("bus");
        String txt_date = getIntent().getStringExtra("date");
        source.setText(txt_source);
        date.setText(txt_date);

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(MainActivity2.this,R.layout.list_item_single,list);
        lv.setAdapter(adapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Buses").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                list.clear();
                for(DocumentSnapshot snap : value) {

                        Buses bus = snap.toObject(Buses.class);
                        ArrayList<String> txt = bus.getTime();
                        for(int i=0;i<txt.size();i++){
                            list.add(txt.get(i));
                        }

                }
                adapter.notifyDataSetChanged();
            }
        });


    }
}