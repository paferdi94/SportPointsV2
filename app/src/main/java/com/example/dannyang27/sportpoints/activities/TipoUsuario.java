package com.example.dannyang27.sportpoints.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.dannyang27.sportpoints.R;

public class TipoUsuario extends AppCompatActivity {

    private Button jugador_btn;
    private Button arbitro_btn;
    private Button centroDeportivo_btn;
    private Button atras_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extra_tipo_usuario);

        //Precargar custom toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        jugador_btn = (Button) findViewById(R.id.jugador_btn);
        arbitro_btn = (Button)findViewById(R.id.arbitro_btn);
        centroDeportivo_btn = (Button) findViewById(R.id.centro_deportivo_btn);
        atras_btn = (Button) findViewById(R.id.atras_btn_1);

        jugador_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        arbitro_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        centroDeportivo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               login();
            }
        });


        atras_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atras();
            }
        });

    }

    //back arrow in a toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Back arrow
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void login(){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    public void atras(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
