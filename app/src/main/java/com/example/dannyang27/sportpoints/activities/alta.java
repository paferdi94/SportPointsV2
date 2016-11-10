package com.example.dannyang27.sportpoints.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dannyang27.sportpoints.R;

public class alta extends AppCompatActivity {

    private Button atras;
    private Button siguiente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extra_alta1);

        atras = (Button) findViewById(R.id.atras_button);
        siguiente = (Button) findViewById(R.id.siguiente_button1);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atras();
            }
        });

        siguiente.setOnClickListener(new View.OnClickListener() {
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
        Intent i = new Intent(this, alta2.class);
        startActivity(i);

    }
}
