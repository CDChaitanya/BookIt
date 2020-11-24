package com.example.bookit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class TicketActivity extends AppCompatActivity
{
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        Display();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Display();
    }

    private void Display()
    {
        listView = findViewById(R.id.listView);
        Intent intent = getIntent();
        String txt_email = intent.getStringExtra("email");

        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(TicketActivity.this, R.layout.list_item, list);
        listView.setAdapter(adapter);

        FirebaseFirestore db =FirebaseFirestore.getInstance();
        db.collection(txt_email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    list.clear();
                    for(QueryDocumentSnapshot document : task.getResult())  // here we're going inside each doc
                    {
                        String s = document.getId();
                        String[] ss = s.split("_");
                        Map<String, Object> map = document.getData();
                        System.out.println(document.getId() + "=>" + document.getData());
                        for (Map.Entry<String, Object> e : map.entrySet())    // here we're going inside each field
                        {
                            String txt_seat = (String) e.getValue();
                            String txt = "Bus: " + ss[0] + "\n"+"Date: " + ss[1] + "               Seat: " + txt_seat + "\n" + "Time: " + ss[2] + "                          Fare: 10";
                            list.add(txt);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
                else
                    Toast.makeText(TicketActivity.this, "Error Getting Document", Toast.LENGTH_SHORT).show();
            }
        });
    }

}