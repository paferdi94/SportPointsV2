package com.example.dannyang27.sportpoints.activities.Equipo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.Jugador;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListarJugadores extends AppCompatActivity {
    private ListView jugadoresLv;
    private List<Jugador> listaJug = new ArrayList<>();
    private HashMap<String,Jugador> hmUsu = new HashMap<>();
    private CustomJugAdapter cAdapter;
    private DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Equipos");
    private EquipoParceable equipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipo_ActivityListarJugadores);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_id_4);
        toolbar.setTitle("LISTA JUGADORES");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        equipo = getIntent().getParcelableExtra("Equipo");

        jugadoresLv = (ListView) findViewById(R.id.listarJugadores_id);
        cAdapter = new CustomJugAdapter(this, listaJug);

        jugadoresLv.setEmptyView(findViewById(android.R.id.empty));
        jugadoresLv.setAdapter(cAdapter);

        jugadoresLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),listaJug.get(position)
                        .getNombre().toString() ,
                        Toast.LENGTH_SHORT).show();
            }
        });
        cargarUsuarios();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Back arrow
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void cargarUsuarios(){
        DatabaseReference usuariosRef = FirebaseDatabase.getInstance().getReference("Usuarios");
        usuariosRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd/MM/yyyy/hh:mm:ss");
                try {
                    Date parsedDate = formatter.parse(map.get("fechaNacimiento").toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date fechaNacimiento = new Date();
                hmUsu.put(dataSnapshot.getKey(),new Jugador(
                        dataSnapshot.getKey(),
                        map.get("extra_Login").toString(),
                        map.get("password").toString(),
                        map.get("nombre").toString(),
                        map.get("apellidos").toString(),
                        map.get("email").toString(),
                        map.get("direccion").toString(),
                        fechaNacimiento
                ));
                cargarJugadores();
            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void cargarJugadores(){
        DatabaseReference eqRef= mRef.child(equipo.getID()).child("jugadores");
        eqRef.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String[] ids = dataSnapshot.getValue().toString().split(",");
                for (String id : ids) {
                    listaJug.add(hmUsu.get(id));
                }
                cAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
