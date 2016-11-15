package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;

public class LogIn extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginBtn;
    private Button registrateBtn;
    private Button facebookBtn;
    private Button gmailBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        emailEditText = (EditText) findViewById(R.id.email_md_main);
        passwordEditText = (EditText) findViewById(R.id.password_md_main);
        loginBtn = (Button) findViewById(R.id.login_md_btn);
        registrateBtn = (Button) findViewById(R.id.registrate_md_btn);
        facebookBtn = (Button) findViewById(R.id.facebook_md_btn);
        gmailBtn = (Button) findViewById(R.id.gmail_md_btn);

        facebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "No implementado", Toast.LENGTH_LONG).show();
            }
        });

        gmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "No implementado", Toast.LENGTH_LONG).show();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPruebaTab();
            }
        });

        registrateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegistro();
            }
        });


    }

    public void showPruebaTab(){
        Intent i = new Intent(this, PruebaTab.class);
        startActivity(i);
    }
    public void showRegistro(){
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }

}
