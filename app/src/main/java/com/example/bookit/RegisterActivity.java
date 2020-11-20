package com.example.bookit;

import androidx.annotation.NonNull;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity
{
    private EditText fname;
    private EditText lname;
    private EditText phone;
    private EditText email;
    private EditText password;
    private Button register;

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);

        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String txt_fname = fname.getText().toString();
                String txt_lname = lname.getText().toString();
                String txt_phone = phone.getText().toString();
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

                if(TextUtils.isEmpty(txt_fname) ||TextUtils.isEmpty(txt_lname) ||TextUtils.isEmpty(txt_phone) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password))
                    Toast.makeText(RegisterActivity.this, "Empty Credentials..!", Toast.LENGTH_SHORT).show();
                else if(txt_password.length() < 6)
                    Toast.makeText(RegisterActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
                else
                    registerUser(txt_fname, txt_lname, txt_phone, txt_email, txt_password);
            }
        });
    }

    private void registerUser(String fname, String lname, String phone, String email, String password)
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put("fname",fname);
        map.put("lname",lname);
        map.put("email",email);
        map.put("phone",phone);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Passenger").document().set(map).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                auth.createUserWithEmailAndPassword(email , password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(RegisterActivity.this, "Registering User", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this  , MainActivity.class));
                            finish();
                        }
                        else
                            Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
