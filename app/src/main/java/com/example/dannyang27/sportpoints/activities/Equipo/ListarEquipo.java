package com.example.dannyang27.sportpoints.activities.Equipo;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.dannyang27.sportpoints.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Map;

import static android.R.attr.data;


public class ListarEquipo extends AppCompatActivity {

    private ListView listar;
    private ArrayAdapter<EquipoParceable> adapter;
    private EquipoItemHolder customAdapter;
    private FloatingActionButton newEquipbtn;
    //private EditText filter;
    private ArrayList<EquipoParceable> listaEquipos = new ArrayList<>();
    private DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
    private String id_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipo_lista);
        Intent intent = getIntent();
        id_usuario = intent.getStringExtra("id_usuario");

        //filter = (EditText) findViewById(R.id.filter_id1);
        newEquipbtn = (FloatingActionButton) findViewById(R.id.newEquipbtn);
        listar = (ListView) findViewById(R.id.equipo_listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaEquipos);

        customAdapter = new EquipoItemHolder(this, listaEquipos);
        listar.setAdapter(customAdapter);
        listar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EquipoParceable e = listaEquipos.get(i);
                showEquipoInfo(e);
            }
        });

        DatabaseReference eRef = mRef.child("Equipos");
        eRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                byte[] decodedString = Base64.decode(map.get("logo").toString(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                EquipoParceable q = new EquipoParceable(
                        dataSnapshot.getKey(),
                        map.get("nombre").toString(),
                        map.get("deporte").toString(),
                        map.get("jugadores").toString(),
                        Integer.parseInt(map.get("max_jugadores").toString()),
                        decodedByte
                );
                listaEquipos.add(q);
                customAdapter.notifyDataSetChanged();
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

        newEquipbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toCrearEquipoView(v);
            }
        });
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

    // Mostrar un equipo.
    private void showEquipoInfo(EquipoParceable e) {
        Intent i = new Intent(this, EquipoInfo.class);
        i.putExtra("PARCELABLE", e);
        startActivity(i);
    }

    // Crear un equipo.
    private void toCrearEquipoView(View v) {
        Intent i = new Intent(this, CrearEquipo.class);
        i.putExtra("id_usuario", id_usuario);
        startActivity(i);
    }
}