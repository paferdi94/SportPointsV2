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
                    Toast.makeText(getApplicationContext(), errores, Toast.LENGTH_SHORT).show();
                }
            }
        });

        ref_db.child("Equipos").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot data, String s) {
                Map<String, Object> map = (Map<String, Object>) data.getValue();
                byte[] decodedString = Base64.decode(map.get("logo").toString(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                Equipo q = new Equipo(data.getKey(),map.get("nombre").toString(),map.get("deporte").toString(),map.get("jugadores").toString(),decodedByte);
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
        Equipo q = new Equipo(id_eq, nombre_equipo.getText().toString(), deportes.getSelectedItem().toString(), id_usuario, logo);
        q.setID(id_eq);
        Map<String, Object> postValuesEq = q.toMap();
        Map<String, Object> childUpdatesEq = new HashMap<>();
        childUpdatesEq.put("/Equipos/" + id_eq, postValuesEq);
        ref_db.updateChildren(childUpdatesEq);
        Intent i = new Intent(this, CorrectoEquipo.class);
        startActivityForResult(i,10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent= getIntent();
        if(resultCode==RESULT_OK && requestCode==10){
            finish();
        }
    }

    public Boolean comprobarDatos() {
        errores = "";
        if(nombre_equipo.getText().toString().equals("")){
            errores+="El nombre del equipo no puede estar vacío.";
            return false;
        }
        for(Equipo q : equipos){
            if(nombre_equipo.getText().toString().equals(q.getNom())) {
                errores += "El nombre del equipo ya existe.";
                return false;
            }
        }
        return true;
    }
}
