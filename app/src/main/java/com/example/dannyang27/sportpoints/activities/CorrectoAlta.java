package com.example.dannyang27.sportpoints.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dannyang27.sportpoints.R;

public class CorrectoAlta extends AppCompatActivity {

    private ImageView img;
    private TextView correcto_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipo_CorrectoAlta);

        img = (ImageView) findViewById(R.id.imageView);
        correcto_txt = (TextView) findViewById(R.id.correcto_txt);

        /*
        Para que no cargue al instante...
        try {
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
         */


    }
}
