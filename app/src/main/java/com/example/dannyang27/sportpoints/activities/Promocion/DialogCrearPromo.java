package com.example.dannyang27.sportpoints.activities.Promocion;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;

public class DialogCrearPromo extends AppCompatActivity {
    private ImageView imagen;
    private FloatingActionButton fab;
    private EditText nombre_et;
    private EditText lugar_et;
    private EditText fecha_In;
    private EditText fecha_Fn;

    private Button cancelar_btn;
    private Button crear_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aaa_activity_dialog_crear_promocion);

        imagen = (ImageView) findViewById(R.id.imagen_dialog_promocion);
        fab = (FloatingActionButton) findViewById(R.id.fab_dialog_promocion);
        nombre_et = (EditText) findViewById(R.id.nombre_dialog_promocion);
        lugar_et = (EditText) findViewById(R.id.lugar_dialog_promocion);
        fecha_In = (EditText) findViewById(R.id.fecha_Init_promocion);
        fecha_Fn = (EditText) findViewById(R.id.fecha_fin);
        cancelar_btn = (Button) findViewById(R.id.cancelarBtn_dialog_promo);
        crear_btn = (Button) findViewById(R.id.crearBtn_dialog_promo);

        crear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Creando Promocion", Toast.LENGTH_LONG).show();
            }
        });

        cancelar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Cancelando Promocion", Toast.LENGTH_LONG).show();
            }
        });


    }
}



















