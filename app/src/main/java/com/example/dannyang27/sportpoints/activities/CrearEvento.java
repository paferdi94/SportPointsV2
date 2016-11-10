package com.example.dannyang27.sportpoints.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.Modelos.Evento;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CrearEvento extends AppCompatActivity {

    private DatabaseReference mDatabase;
    TextView txtDeporte;
    TextView txtNombre;
    TextView txtCapacidad;
    TextView txtDescripcion;
    TextView txtLugar;
    TextView txtFecha;
    TextView txtHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evento_crear);

        //Precargar custom toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.add_evento_toolbar));
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Obtener referencia a Firebase/Eventos
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Inicializar variables
        txtDeporte = (TextView) findViewById(R.id.txt_deporte);
        txtHora = (TextView) findViewById(R.id.txt_deporte);
        txtNombre = (TextView) findViewById(R.id.txt_hora);
        txtCapacidad = (TextView) findViewById(R.id.txt_capacidad);
        txtDescripcion = (TextView) findViewById(R.id.txt_descripcion);
        txtLugar = (TextView) findViewById(R.id.txt_lugar);
        txtFecha = (TextView) findViewById(R.id.txt_fecha);
        txtNombre = (TextView) findViewById(R.id.txt_nombre);


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

    public void btnCrearEvento_onclick(View v) {

        //Generar tabla con ID  y obtener el valor
        String key = mDatabase.child("Eventos").push().getKey();

        //Evento(capacidadActual,capacidadMaxima,descripcion,fecha,hora,lugar,deporte,usuario
        Evento evento = new Evento(Integer.parseInt(txtCapacidad.getText().toString()),
                Integer.parseInt(txtCapacidad.getText().toString()), txtDescripcion.getText().toString(),
                txtFecha.getText().toString(), txtHora.getText().toString(), txtLugar.getText().toString(),
                txtDeporte.getText().toString(), txtNombre.getText().toString());

        Map<String, Object> postValues = evento.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Eventos/" + key, postValues);


        mDatabase.updateChildren(childUpdates);

    }

}
