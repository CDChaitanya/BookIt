package com.example.bookit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class EditActivity extends AppCompatActivity
{
    private EditText fname;
    private EditText lname;
    private EditText phone;
    private Button done_btn;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        phone = findViewById(R.id.phone);
        done_btn = findViewById(R.id.done_btn);

        FirebaseUser currentUser= FirebaseAuth.getInstance().getCurrentUser();
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
                        s = snap.getId();
                        //Toast.makeText(EditActivity.this, s, Toast.LENGTH_SHORT).show();
                        fname.setText(snap.getString("fname"));
                        lname.setText(snap.getString("lname"));
                        phone.setText(snap.getString("phone"));
                    }
                }
            }
        });

        done_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String txt_fname = fname.getText().toString();
                String txt_lname = lname.getText().toString();
                String txt_phone = phone.getText().toString();

                if(TextUtils.isEmpty(txt_fname) || TextUtils.isEmpty(txt_lname) || TextUtils.isEmpty(txt_phone))
                    Toast.makeText(EditActivity.this, "Empty Credentials", Toast.LENGTH_SHORT).show();
                else
                {
                    DocumentReference ref = FirebaseFirestore.getInstance().collection("Passenger").document(s);
                    ref.update("fname",txt_fname);
                    ref.update("lname",txt_lname);
                    ref.update("phone",txt_phone).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            startActivity(new Intent(EditActivity.this , MainActivity.class));
                            //Toast.makeText(EditActivity.this, "Info Updated", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}