package com.example.bookit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity
{
    private Button register;
    private Button login;
    private long pressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        register = findViewById(R.id.register);
        login = findViewById(R.id.login);

        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(StartActivity.this , RegisterActivity.class));
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(StartActivity.this , LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null)
        {
            startActivity(new Intent(StartActivity.this , MainActivity.class));
            finish();
        }
    }

    @Override
    public void onBackPressed()
    {
        if(pressedTime+2000 > System.currentTimeMillis())
        {
            super.onBackPressed();
            finish();
        }
        else
        {
            Toast.makeText(this, "Press Back Again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }
}
