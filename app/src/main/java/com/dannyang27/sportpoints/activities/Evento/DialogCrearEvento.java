package com.dannyang27.sportpoints.activities.Evento;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dannyang27.sportpoints.R;

public class DialogCrearEvento extends AppCompatActivity {
    private ImageView imagen;
    private FloatingActionButton fab;
    private EditText nombre_et;
    private EditText lugar_et;
    private EditText fecha_et;
    private EditText hora_et;

    private Button cancelar_btn;
    private Button crear_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aaa_activity_dialog_crear_evento);

        imagen = (ImageView) findViewById(R.id.imagen_dialog_evento);
        fab = (FloatingActionButton) findViewById(R.id.fab_dialog_evento);
        nombre_et = (EditText) findViewById(R.id.nombre_dialog_evento);
        lugar_et = (EditText) findViewById(R.id.lugar_dialog_evento);
        hora_et = (EditText) findViewById(R.id.hora_dialog_evento);
        fecha_et = (EditText) findViewById(R.id.fecha_dialog_evento);
        cancelar_btn = (Button) findViewById(R.id.cancelarBtn_dialog_evento);
        crear_btn = (Button) findViewById(R.id.crearBtn_dialog_evento);

        crear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Creando evento", Toast.LENGTH_LONG).show();
            }
        });

        cancelar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Cancelando evento", Toast.LENGTH_LONG).show();
            }
        });


    }
}



















