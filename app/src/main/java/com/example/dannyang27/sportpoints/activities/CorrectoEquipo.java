package com.example.dannyang27.sportpoints.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dannyang27.sportpoints.R;

public class CorrectoEquipo extends AppCompatActivity {

    private ImageView img;
    private TextView correcto_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.correcto_equipo);

        img = (ImageView) findViewById(R.id.imageView);
        correcto_txt = (TextView) findViewById(R.id.correcto_txt);
    }


}
