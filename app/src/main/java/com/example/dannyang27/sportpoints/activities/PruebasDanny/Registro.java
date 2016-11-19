package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;

public class Registro extends AppCompatActivity {
    private ImageButton profile;
    private EditText nombre;
    private EditText email;
    private EditText pass1;
    private EditText pass2;
    private EditText fechaNacimiento;
    private EditText telefono;
    private Button cancelar_btn;
    private Button aceptar_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_md);

        profile = (ImageButton) findViewById(R.id.imageBtn_md_registro);
        nombre = (EditText) findViewById(R.id.nombre_md_registro);
        email = (EditText) findViewById(R.id.email_md_registro);
        pass1 = (EditText) findViewById(R.id.password_md_registro);
        pass2 = (EditText) findViewById(R.id.password2_md_registro);
        fechaNacimiento = (EditText) findViewById(R.id.fecha_md_registro);
        telefono = (EditText) findViewById(R.id.telefono_md_registro);
        cancelar_btn = (Button) findViewById(R.id.cancelar_md_registro);
        aceptar_btn = (Button) findViewById(R.id.aceptar_md_regsitro);

        cancelar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Cancelando...", Toast.LENGTH_SHORT).show();
            }
        });

        aceptar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Usuario creado correctamente", Toast.LENGTH_SHORT).show();
                goMain();
            }
        });


    }

    private void goMain() {
        Intent i = new Intent(this, LogIn.class);
        startActivity(i);
    }
}
