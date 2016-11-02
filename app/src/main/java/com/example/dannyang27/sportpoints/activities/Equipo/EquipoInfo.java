package com.example.dannyang27.sportpoints.activities.Equipo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.CorrectoUnirseEquipo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EquipoInfo extends AppCompatActivity {

    private TextView nombre_equipo;
    private TextView deporte;
    private TextView jugadores;
    private ImageView img_equipo;
    private Button joinBtn;
    private EquipoParceable equipo;
    private String id_usuario;
    int EQUIPO_UNIDO_CORRECTO = 30;
    private String errores;

    private DatabaseReference ref_db = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipo_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_id_3);
        toolbar.setTitle("INFORMACION EQUIPO");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nombre_equipo = (TextView) findViewById(R.id.nombre_equipo);
        deporte = (TextView) findViewById(R.id.deporte);
        jugadores = (TextView) findViewById(R.id.jugadores);
        img_equipo = (ImageView) findViewById(R.id.img_equipo);
        joinBtn = (Button) findViewById(R.id.joinBtn);

        equipo = getIntent().getParcelableExtra("Equipo");
        id_usuario = getIntent().getStringExtra("Usuario");

        nombre_equipo.setText(equipo.getNom());
        deporte.setText("Deporte: "+equipo.getDeporte());
        if(equipo.getMaxJugadores()==-1){
            jugadores.setText("Jugadores: "+equipo.getJugadores().size()+"/∞");
        }else{
            jugadores.setText("Jugadores: "+equipo.getJugadores().size()+"/"+equipo.getMaxJugadores());
        }
        img_equipo.setImageBitmap(equipo.getLogo());

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (comprobarDatos()) {
                    unirse();
                }else{
                    Toast.makeText(getApplicationContext(), errores, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void correctoUnirseEquipo(){
        //Intent i = new Intent(this, CorrectoUnirseEquipo.class);
        //startActivityForResult(i,EQUIPO_UNIDO_CORRECTO);
        Toast.makeText(getApplicationContext(), "Te has unido correctamente al equipo.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = getIntent();
        if(resultCode==RESULT_OK && requestCode==EQUIPO_UNIDO_CORRECTO){
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Añadir icono al actionBar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.info_jugadores_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.info_jugadores:
                goListarJugadores(equipo);
                return true;
            case android.R.id.home:
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goListarJugadores(EquipoParceable e) {
        if(equipo.getJugadores().size()>0) {
            Intent i = new Intent(this, ListarJugadores.class);
            i.putExtra("Equipo", e);
            startActivity(i);
        }else{
            Toast.makeText(getApplicationContext(), "No hay ningun jugador.", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean comprobarDatos(){
        errores = "";
        if(equipo.getJugadores().toString().contains(id_usuario)){
            errores+="Ya estas dentro del equipo.";
            return false;
        }
        return true;
    }

    private void unirse(){
        equipo.addJugadores(id_usuario);
        Map<String, Object> postValuesEq = equipo.toMap();
        Map<String, Object> childUpdatesEq = new HashMap<>();
        childUpdatesEq.put("/Equipos/" + equipo.getID(), postValuesEq);
        ref_db.updateChildren(childUpdatesEq);
        correctoUnirseEquipo();
    }
}
