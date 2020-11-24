package com.example.bookit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class MainActivity3 extends AppCompatActivity
{
    private TextView source;
    private TextView destination;
    private TextView date;
    private TextView time;
    private Button book_btn;
    Vector<ImageView> xx = new Vector<>();

    private ImageView a1;private ImageView b1;private ImageView c1;private ImageView d1;
    private ImageView a2;private ImageView b2;private ImageView c2;private ImageView d2;
    private ImageView a3;private ImageView b3;private ImageView c3;private ImageView d3;
    private ImageView a4;private ImageView b4;private ImageView c4;private ImageView d4;
    private ImageView a5;private ImageView b5;private ImageView c5;private ImageView d5;
    private ImageView a6;private ImageView b6;private ImageView c6;private ImageView d6;
    private ImageView a7;private ImageView b7;private ImageView c7;private ImageView d7;
    private ImageView a8;private ImageView b8;private ImageView c8;private ImageView d8;
    private ImageView a9;private ImageView b9;private ImageView c9;private ImageView d9;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        a1 = findViewById(R.id.a1);b1 = findViewById(R.id.b1);c1 = findViewById(R.id.c1);d1 = findViewById(R.id.d1);
        a2 = findViewById(R.id.a2);b2 = findViewById(R.id.b2);c2 = findViewById(R.id.c2);d2 = findViewById(R.id.d2);
        a3 = findViewById(R.id.a3);b3 = findViewById(R.id.b3);c3 = findViewById(R.id.c3);d3 = findViewById(R.id.d3);
        a4 = findViewById(R.id.a4);b4 = findViewById(R.id.b4);c4 = findViewById(R.id.c4);d4 = findViewById(R.id.d4);
        a5 = findViewById(R.id.a5);b5 = findViewById(R.id.b5);c5 = findViewById(R.id.c5);d5 = findViewById(R.id.d5);
        a6 = findViewById(R.id.a6);b6 = findViewById(R.id.b6);c6 = findViewById(R.id.c6);d6 = findViewById(R.id.d6);
        a7 = findViewById(R.id.a7);b7 = findViewById(R.id.b7);c7 = findViewById(R.id.c7);d7 = findViewById(R.id.d7);
        a8 = findViewById(R.id.a8);b8 = findViewById(R.id.b8);c8 = findViewById(R.id.c8);d8 = findViewById(R.id.d8);
        a9 = findViewById(R.id.a9);b9 = findViewById(R.id.b9);c9 = findViewById(R.id.c9);d9 = findViewById(R.id.d9);

        Vector<ImageView> seats = new Vector<>();
        seats.add(a1);seats.add(b1);seats.add(c1);seats.add(d1);
        seats.add(a2);seats.add(b2);seats.add(c2);seats.add(d2);
        seats.add(a3);seats.add(b3);seats.add(c3);seats.add(d3);
        seats.add(a4);seats.add(b4);seats.add(c4);seats.add(d4);
        seats.add(a5);seats.add(b5);seats.add(c5);seats.add(d5);
        seats.add(a6);seats.add(b6);seats.add(c6);seats.add(d6);
        seats.add(a7);seats.add(b7);seats.add(c7);seats.add(d7);
        seats.add(a8);seats.add(b8);seats.add(c8);seats.add(d8);
        seats.add(a9);seats.add(b9);seats.add(c9);seats.add(d9);

        date = findViewById(R.id.textView3);
        time = findViewById(R.id.time);
        source = findViewById(R.id.source);
        destination = findViewById(R.id.destination);
        book_btn = findViewById(R.id.book_btn);

        Intent intent = getIntent();
        String txt_time = intent.getStringExtra("time");
        String txt_date = intent.getStringExtra("date");
        String txt_bus = intent.getStringExtra("busName");

        date.setText(txt_date);
        time.setText(txt_time);
        String[] strs = txt_bus.split("To");
        source.setText(strs[0]);
        destination.setText(strs[1]);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference ref = db.collection(txt_bus).document(txt_date+txt_time);
        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists())
                    {
                        for (int i=0;i<seats.size();i++)
                        {
                            ImageView z= seats.elementAt(i);
                            int text = z.getId();
                            String s =getResources().getResourceName(text);
                            String[] ss = s.split("/");
                            System.out.println(ss[0]+"##############^^^^");
                            System.out.println(ss[1]+"##############^^^^");
                            //System.out.println(document.getString(ss[1])+"##############^^^^");
                            if(document.get(ss[1]) != null) {
                                if (document.get(ss[1]).toString().equals(Integer.toString(2))) {
                                    z.setImageResource(R.drawable.booked_img);
                                    z.setClickable(false);
                                }
                            }
                        }
                    }
                }
            }
        });

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(a1);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(b1);
            }
        });
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(c1);
            }
        });
        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(d1);
            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(a2);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(b2);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(c2);
            }
        });
        d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(d2);
            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(a3);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(b3);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(c3);
            }
        });
        d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(d3);
            }
        });

        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(a4);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(b4);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(c4);
            }
        });
        d4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(d4);
            }
        });

        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(a5);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(b5);
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(c5);
            }
        });
        d5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(d5);
            }
        });

        a6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(a6);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(b6);
            }
        });
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(c6);
            }
        });
        d6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(d6);
            }
        });

        a7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(a7);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(b7);
            }
        });
        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(c7);
            }
        });
        d7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(d7);
            }
        });

        a8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(a8);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(b8);
            }
        });
        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(c8);
            }
        });
        d8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(d8);
            }
        });

        a9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(a9);
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(b9);
            }
        });
        c9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(c9);
            }
        });
        d9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectseat(d9);
            }
        });


        book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference ref = db.collection(txt_bus).document(txt_date+txt_time);
                ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful())
                        {
                            DocumentSnapshot document = task.getResult();
                            int text1 = xx.elementAt(0).getId();
                            String s1 =getResources().getResourceName(text1);
                            String[] ss = s1.split("/");
                            HashMap<String , Object> data = new HashMap<>();
                            data.put(ss[1],2);

                            if(document.exists())
                            {
                                db.collection(txt_bus).document(txt_date+txt_time).set(data , SetOptions.merge()); // ITHE PAHILA CHECK KArYCHA AHE
                            }
                            else{
                                db.collection(txt_bus).document(txt_date+txt_time).set(data);
                            }
                        }
                        else
                            System.out.println("Failed " + task.getException());
                    }
                });

                FirebaseUser currentUser= FirebaseAuth.getInstance().getCurrentUser();
                String txt_email = currentUser.getEmail();

                DocumentReference ref1 = db.collection(txt_email).document(txt_bus + "_" + txt_date+"_"+txt_time);
                ref1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            int text1 = xx.elementAt(0).getId();
                            String s1 = getResources().getResourceName(text1);
                            String[] ss = s1.split("/");
                            HashMap<String, Object> data = new HashMap<>();
                            data.put("seat"+ss[1], ss[1]);

                            if (document.exists()) {
                                db.collection(txt_email).document(txt_bus + "_" + txt_date + "_" + txt_time).set(data, SetOptions.merge()); // ITHE PAHILA CHECK KArYCHA AHE
                            } else {
                                db.collection(txt_email).document(txt_bus + "_" + txt_date + "_" + txt_time).set(data);
                            }
                        }
                    }
                });
                Toast.makeText(MainActivity3.this, "Ticket Booked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity3.this , MainActivity.class));
                finish();
            }
        });

    }

    public void selectseat(ImageView x)
    {
        int text = x.getId();
        String s =getResources().getResourceName(text);
        System.out.println(s+"++++++++++");
        if(xx.isEmpty())
        {
            x.setImageResource(R.drawable.your_seat_img);
            xx.add(x);
            System.out.println(xx.elementAt(0)+"############");
        }
        else
        {
            int text1 = xx.elementAt(0).getId();
            String s1 =getResources().getResourceName(text1);

            if(! s.equals(s1)){
                ImageView z = xx.elementAt(0);
                z.setImageResource(R.drawable.available_img);
                xx.remove(0);
                x.setImageResource(R.drawable.your_seat_img);
                xx.add(x);
            }
            else if(s.equals(s1)){
                System.out.println(xx.get(0)+"############");
                x.setImageResource(R.drawable.available_img);
                xx.remove(0);
            }
        }
    }
}