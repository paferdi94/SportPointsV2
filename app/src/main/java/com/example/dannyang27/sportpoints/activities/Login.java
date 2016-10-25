package com.example.dannyang27.sportpoints.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dannyang27.sportpoints.R;

public class Login extends AppCompatActivity {

    private EditText email_field;
    private EditText password_field;
    private Button login_btn;
    private Button atras_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        email_field = (EditText) findViewById(R.id.email_editText);
        password_field = (EditText) findViewById(R.id.contrasenya_editText);
        login_btn = (Button) findViewById(R.id.login_btn);
        atras_btn = (Button) findViewById(R.id.atras_btn_2);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logearse();
            }
        });

        atras_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atras();
            }
        });
    }

    public void logearse(){
        Intent i = new Intent(this, Principal.class);
        startActivity(i);
    }
    public void atras(){
        Intent i = new Intent(this, TipoUsuario.class);
        startActivity(i);
    }

    public static class EventoInfo extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.evento_info);
        }
    }
}
