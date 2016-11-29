package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.Modelos.EquipoPruebaDanny;
import com.example.dannyang27.sportpoints.activities.Modelos.EventoPruebaDanny;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class EquipoInfo_MD extends AppCompatActivity {

    private ImageView imagenEquipo;
    private TextView nombreTxt;
    private TextView deporteTxt;
    private TextView participantesTxt;
    private Button verPartBtn;
    private Button unirseBtn;
    private TextView descripcionTxt;
    private int cap=0;

    private Toolbar tb;

    private String nombre_key;
    private FirebaseAuth mAuth;

    private ArrayList<String> listaParticipantes = new ArrayList<>();


    FirebaseStorage firebaseStorageRef = FirebaseStorage.getInstance();
    StorageReference mStorageRef =firebaseStorageRef.getReference();

    FirebaseDatabase mDataRef = FirebaseDatabase.getInstance();
    DatabaseReference participantesRef = mDataRef.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo_info__md);
        final DatabaseReference equiposRef = mDataRef.getReference().child("Equipos");

        mAuth = FirebaseAuth.getInstance();

        tb = (Toolbar) findViewById(R.id.toolbar_equipoInfo);

        nombreTxt = (TextView) findViewById(R.id.nombre_equipo_info_md);
        imagenEquipo = (ImageView) findViewById(R.id.image_equipo_info_md);
        deporteTxt = (TextView) findViewById(R.id.deporte_equipo_info_md);
        participantesTxt = (TextView) findViewById(R.id.participantes_equipo_info_md);
        verPartBtn = (Button) findViewById(R.id.verParticipantes_equipo_info_md);
        unirseBtn = (Button) findViewById(R.id.unirse_equipo_info_md);
        descripcionTxt = (TextView) findViewById(R.id.descripcion_equipo_info_md);

        /////////////////////Añadir Toolbar////////////////////////
        tb.setTitle("Información de Equipo");
        setSupportActionBar(tb);
        tb.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //////////////////////////////////////////////////////////


        final EquipoPruebaDanny e = getIntent().getParcelableExtra("PARCELABLE");

        nombre_key = e.getNombre();

        mStorageRef = mStorageRef.child("equipos/"+e.getImagen());
        participantesRef = participantesRef.child("Equipos")
                .child(e.getNombre().toString()).child("participantes");

        participantesRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String str = dataSnapshot.getValue(String.class).toString();
                listaParticipantes.add(str);
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


        nombreTxt.setText(e.getNombre());
        deporteTxt.setText(e.getDeporte());
        //participantesTxt.setText(e.getCapacidadActual()+" / "+e.getCapacidadMaxima());
        //descripcionTxt.setText(e.getD);




        mStorageRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imagenEquipo.setImageBitmap(bmp);

            }
        });



    verPartBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           //showParticipantesInfo();
            //Toast.makeText(getApplicationContext(),getListaParticipantes(),Toast.LENGTH_LONG).show();
            showListarParticipantes(listaParticipantes);
        }
    });

        unirseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view,"Te has unido al equipo correctamente", Snackbar.LENGTH_LONG).show();
                if(!e.getParticipantes().contains(mAuth.getCurrentUser().getEmail())) {
                    e.getParticipantes().add(mAuth.getCurrentUser().getEmail());
                    //DatabaseReference auxRef= equiposRef.child(nombre_key);

                    equiposRef.child(nombre_key).setValue(e);
                    Snackbar.make(view,"Te has unido al equipo correctamente", Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(view,"Ya estas dentro del equipo", Snackbar.LENGTH_LONG).show();
                }


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

    private void showListarParticipantes(ArrayList<String> a) {
        Intent i = new Intent(this, VerParticipantesProvisional.class);
        i.putStringArrayListExtra("listaParticipantes", listaParticipantes);
        startActivity(i);
    }

    private void showParticipantesInfo() {
        Intent i = new Intent(this, PruebaListarParticipantes.class);
        startActivity(i);
    }

    private String getListaParticipantes(){
        String aux = "";
        for(int i=0; i < listaParticipantes.size();i++){
            aux += listaParticipantes.get(i)+"\n";

        }
        return aux;
    }

    private int getSize(){

        return cap;
    }



}
