package com.example.bookit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity
{
    private TextView heytv;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heytv = findViewById(R.id.heytv);

        /*FirebaseFirestore db = FirebaseFirestore.getInstance();
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<String> arrayList = new ArrayList<String>();
        String[] strs ={"05:55","06:05","06:15","06:25","06:35","06:45","06:50","06:55","07:00","07:05","07:10","07:15","07:20","07:25","07:30","07:45","07:50","07:55","08:00","08:05","08:10","08:15","08:20","08:25", "08:35","08:50","09:00","09:05","09:10","09:15","09:20","09:25","09:30","09:35","09:45","10:00", "10:10","10:15","10:20","10:25","10:30","10:35","10:45","10:55","11:10","11:20","11:30","11:40","11:55","12:05","12:10","12:15","12:20","12:25","12:35","12:50","13:00","13:05","13:10","13:15","13:20","13:25","13:30","13:35","13:40","13:55","14:00","14:05","14:10","14:15","14:20","14:25"};

        for(int i=0;i<strs.length;i++)
            arrayList.add(strs[i]);

        map.put("time",arrayList);
        db.collection("Buses").document("Swargate To Pune Station").set(map).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                Toast.makeText(MainActivity.this, "Swargate To Pune Station", Toast.LENGTH_SHORT).show();
            }
        });*/

        FirebaseUser currentUser=FirebaseAuth.getInstance().getCurrentUser();
        String txt_email = currentUser.getEmail();

        FirebaseFirestore db =FirebaseFirestore.getInstance();
        db.collection("Passenger").addSnapshotListener(new EventListener<QuerySnapshot>()
        {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error)
            {
                for(DocumentSnapshot snap : value)
                {
                    System.out.println(snap.getString("id")+" " +txt_email.equals(snap.getString("email")) + txt_email + "   ############");
                    if( txt_email.equalsIgnoreCase(snap.getString("email")) )
                    {
                        heytv.setText("Hey "+snap.getString("fname")+"..!!");
                    }
                }
            }
        });
    }
}