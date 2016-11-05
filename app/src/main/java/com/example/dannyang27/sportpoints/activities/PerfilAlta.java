package com.example.dannyang27.sportpoints.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dannyang27.sportpoints.R;

public class PerfilAlta extends AppCompatActivity {

    private EditText nombre_field;
    private EditText apellidos_field;
    private EditText direccion_field;
    private EditText fechaNacimiento_field;
    private ImageView foto_perfil;
    private Button subir_img_btn;
    private Button atras_btn;
    private Button sig_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extra_PerfilAlta);

        nombre_field = (EditText) findViewById(R.id.nombre_field);
        apellidos_field = (EditText) findViewById(R.id.apellidos_field);
        direccion_field = (EditText) findViewById(R.id.direccion_field);
        fechaNacimiento_field = (EditText) findViewById(R.id.fechaNac_field);
        foto_perfil = (ImageView)findViewById(R.id.profile_img);
        subir_img_btn = (Button) findViewById(R.id.subir_btn);
        atras_btn = (Button) findViewById(R.id.atras_btn_1);
        sig_btn = (Button) findViewById(R.id.sig_btn_1);


        /*
        MAS ADELANTE CUANDO QUERAMOS PASAR UNA CLASE COMO EXTRA EN UN INTENT

        final Jugador jugador = new Jugador();
        jugador.setNombre(nombre_field.getText().toString());
        jugador.setApellidos(apellidos_field.getText().toString());
        jugador.setDireccion(direccion_field.getText().toString());
        jugador.setFechaNacimiento(fechaNacimiento_field.getText().toString());
        jugador.setEmail("");
        jugador.setPassword("");

         */


        atras_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atras();
            }
        });

        sig_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                siguiente();
            }
        });


    }
    public void atras(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    public void siguiente(){
        Intent i = new Intent(this, Deportes.class);
        startActivity(i);

    }
}
