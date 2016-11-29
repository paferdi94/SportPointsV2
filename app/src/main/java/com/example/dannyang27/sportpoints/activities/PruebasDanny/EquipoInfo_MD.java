package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.Modelos.EquipoPruebaDanny;
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

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class EquipoInfo_MD extends AppCompatActivity {

    private ImageView imagenEquipo;
    private TextView nombreTxt;
    private TextView deporteTxt;
    private TextView participantesTxt;
    private TextView unirse_a_equipo;
    private Button verPartBtn;
    private Button unirseBtn;
    private Button bajaBtn;
    private TextView descripcionTxt;
    String emailLogeado;
    private View rootView;
    String participanteKey;
    Boolean usuarioEncontrado = false;
    private int cap = 0;

    private Toolbar tb;

    private String nombre_key;
    private FirebaseAuth mAuth;

    private ArrayList<String> listaParticipantes = new ArrayList<>();


    FirebaseStorage firebaseStorageRef = FirebaseStorage.getInstance();
    StorageReference mStorageRef = firebaseStorageRef.getReference();

    FirebaseDatabase mDataRef = FirebaseDatabase.getInstance();
    DatabaseReference participantesRef = mDataRef.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo_info__md);
        final DatabaseReference equiposRef = mDataRef.getReference().child("Equipos");

        mAuth = FirebaseAuth.getInstance();
        rootView = (View) findViewById(R.id.activity_equipo_info__md);
        tb = (Toolbar) findViewById(R.id.toolbar_equipoInfo);

        nombreTxt = (TextView) findViewById(R.id.nombre_equipo_info_md);
        imagenEquipo = (ImageView) findViewById(R.id.image_equipo_info_md);
        deporteTxt = (TextView) findViewById(R.id.deporte_equipo_info_md);
        participantesTxt = (TextView) findViewById(R.id.participantes_equipo_info_md);
        verPartBtn = (Button) findViewById(R.id.verParticipantes_equipo_info_md);
        unirseBtn = (Button) findViewById(R.id.unirse_equipo_info_md);
        //bajaBtn = (Button) findViewById(R.id.baja_equipo_info_md);
        descripcionTxt = (TextView) findViewById(R.id.descripcion_equipo_info_md);
        unirse_a_equipo = (TextView) findViewById(R.id.unirse_equipo_info_md);

        /////////////////////Añadir Toolbar////////////////////////
        tb.setTitle("Información de Equipo");
        setSupportActionBar(tb);
        tb.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //////////////////////////////////////////////////////////


        final EquipoPruebaDanny e = getIntent().getParcelableExtra("PARCELABLE");

        nombre_key = e.getNombre();
        emailLogeado = mAuth.getCurrentUser().getEmail();

        mStorageRef = mStorageRef.child("equipos/" + e.getImagen());
        participantesRef = participantesRef.child("Equipos")
                .child(e.getNombre().toString()).child("participantes");

        participantesRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String str = dataSnapshot.getValue(String.class).toString();
                int key = Integer.parseInt(dataSnapshot.getKey().toString());
                if (!usuarioEncontrado && emailLogeado.equals(str)) {
                    unirse_a_equipo.setText("DEJAR");
                    usuarioEncontrado = true;
                    participanteKey = dataSnapshot.getKey();
                } else {
                    listaParticipantes.add(str);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String usuarioBorrado = dataSnapshot.getValue(String.class).toString();
                Log.d("SportPoints", usuarioBorrado);
                borrarElementoLista(usuarioBorrado);
                e.setParticipantes(listaParticipantes);
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
                // showListarParticipantes(listaParticipantes);

                showListarParticipantes_beta(e);
            }
        });

        unirseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (unirse_a_equipo.getText().equals("DEJAR")) {
                    new AlertDialog.Builder(view.getContext())
                            .setTitle("Dejar Equipo")
                            .setMessage("Estás seguro que salir del equipo?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    if (isOnlineNet()) {
                                        participantesRef.child(participanteKey).removeValue();
                                        usuarioEncontrado = false;
                                        unirse_a_equipo.setText("UNIRSE");
                                        Snackbar.make(rootView, "Has salido del equipo correctamente", Snackbar.LENGTH_LONG).show();
                                    } else
                                        Snackbar.make(rootView, "Problemas de conexión, inténtelo más tarde...", Snackbar.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                } else {
                    // Snackbar.make(view,"Te has unido al equipo correctamente", Snackbar.LENGTH_LONG).show();
                    if (!e.getParticipantes().contains(mAuth.getCurrentUser().getEmail())) {
                        e.getParticipantes().add(mAuth.getCurrentUser().getEmail());
                        //DatabaseReference auxRef= equiposRef.child(nombre_key);

                        equiposRef.child(nombre_key).setValue(e);
                        Snackbar.make(view, "Te has unido al equipo correctamente", Snackbar.LENGTH_LONG).show();
                    } else {
                        Snackbar.make(view, "Ya estas dentro del equipo", Snackbar.LENGTH_LONG).show();
                    }
                }

            }
        });

//        bajaBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Toast.makeText(getApplicationContext(), "pulsado", Toast.LENGTH_LONG).show();
//
//
//                Toast.makeText(view.getContext(), "!das", Toast.LENGTH_LONG).show();
//
//                //participantesRef.child(key).removeValue();
//
//
//            }
//        });
    }
    /*
        public int devolverKey(String str){
        int k=-1;
        for(int i=0; i<arrayParticipantes.length;i++){
            if(arrayParticipantes[i].equals(str)) {
                return i;
            }
        }
        return k;

    }
     */


    private void showListarParticipantes_beta(EquipoPruebaDanny e) {
        Intent i = new Intent(this, VerParticipantesProvisional.class);
        i.putExtra("PARCELABLE", e);
        startActivity(i);
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

    private String getListaParticipantes() {
        String aux = "";
        for (int i = 0; i < listaParticipantes.size(); i++) {
            aux += listaParticipantes.get(i) + "\n";

        }
        return aux;
    }

    private int getSize() {

        return cap;
    }

    private void borrarElementoLista(String usuario) {
        for (int i = 0; i < listaParticipantes.size(); i++) {
            if (usuario.equals(listaParticipantes.get(i))) {
                listaParticipantes.remove(i);
                Log.d("SportPoints", usuario);
            }
        }
    }

    //Comprobar si tenemos internet en un momento determinado
    public Boolean isOnlineNet() {
        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");
            int val = p.waitFor();
            boolean reachable = (val == 0);
            if (!reachable) {
                p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.upv.es");
                val = p.waitFor();
                reachable = (val == 0);
            }
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}