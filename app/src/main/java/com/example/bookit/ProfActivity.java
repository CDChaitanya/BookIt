package com.example.bookit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class ProfActivity extends AppCompatActivity
{
    private TextView fullname;
    private Button logout;
    private Button editprof;
    private Button my_tickets;
    private Button aboutus;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);

        fullname = findViewById(R.id.fullname);
        logout =findViewById(R.id.log_out);
        editprof = findViewById(R.id.editprof);
        my_tickets = findViewById(R.id.my_tickets);
        aboutus = findViewById(R.id.aboutus);

        Intent intent = getIntent();
        String s = intent.getStringExtra("email");

        FirebaseFirestore db =FirebaseFirestore.getInstance();
        db.collection("Passenger").addSnapshotListener(new EventListener<QuerySnapshot>()
        {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error)
            {
                for(DocumentSnapshot snap : value)
                {
                    System.out.println(snap.getString("id")+" " +s.equals(snap.getString("email")) + s + "   ############");
                    if( s.equalsIgnoreCase(snap.getString("email")) )
                    {
                        fullname.setText(snap.getString("fname")+" "+snap.getString("lname") );
                    }
                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(ProfActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ProfActivity.this , StartActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
            }
        });

        editprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfActivity.this , EditActivity.class));
            }
        });

        my_tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent xx = new Intent(ProfActivity.this , TicketActivity.class);
                xx.putExtra("email",s);
                startActivity(xx);
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfActivity.this , AboutActivity.class));
            }
        });
    }
}