package com.example.dannyang27.sportpoints.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.example.dannyang27.sportpoints.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CrearEquipo extends AppCompatActivity {
    private EditText nombre_equipo;
    private Spinner deportes;
    private ImageView img_equipo;
    private Button cambiar_img_btn;
    private Button cancelar_btn;
    private Button crear_btn;
    private String id_usuario;
    private String errores;
    private ArrayList<Equipo> equipos = new ArrayList<Equipo>();
    private DatabaseReference ref_db = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_equipo);
        Intent intent = getIntent();
        id_usuario = intent.getStringExtra("id_usuario");

        deportes = (Spinner) findViewById(R.id.deporte_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.deportes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deportes.setAdapter(adapter);
        nombre_equipo = (EditText) findViewById(R.id.nombre_equipo_field);
        img_equipo = (ImageView) findViewById(R.id.img_equipo);
        cambiar_img_btn = (Button) findViewById(R.id.cambiar_img_btn);
        cancelar_btn = (Button) findViewById(R.id.cancelar_btn);
        crear_btn = (Button) findViewById(R.id.crear_btn);

        cancelar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelar();
            }
        });
        crear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (comprobarDatos()) {
                    crear();
                }else{
                    // Mostrar errores.
                }
            }
        });

        ref_db.child("Equipos").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot data, String s) {
                Equipo q = data.getValue(Equipo.class);
                q.setID(data.getKey());
                equipos.add(q);
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

    public void cancelar() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void crear() {
        String id_eq = ref_db.child("Equipos").push().getKey();
        Bitmap logo = Bitmap.createBitmap(img_equipo.getWidth(),img_equipo.getHeight(),Bitmap.Config.ARGB_8888);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        logo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        String logo_b64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
        Equipo q = new Equipo(id_eq, nombre_equipo.getText().toString(), deportes.getSelectedItem().toString(), id_usuario, logo_b64);
        Map<String, Object> postValuesEq = q.toMap();
        Map<String, Object> childUpdatesEq = new HashMap<>();
        childUpdatesEq.put("/Equipos/" + id_eq, postValuesEq);
        ref_db.updateChildren(childUpdatesEq);

        Intent i = new Intent(this, CorrectoEquipo.class);
        startActivity(i);
    }

    public Boolean comprobarDatos() {
        errores = "";
        if(nombre_equipo.getText().toString()==""){
            errores+="El nombre del equipo no puede ser vacío.\n";
        }
        for(Equipo q : equipos){
            if(nombre_equipo.getText().toString().equals(q.getNom())) {
                errores += "El nombre del equipo ya existe.\n";
                break;
            }
        }
        if(errores.equals("")){
            return true;
        }else{
            return false;
        }
    }
}
