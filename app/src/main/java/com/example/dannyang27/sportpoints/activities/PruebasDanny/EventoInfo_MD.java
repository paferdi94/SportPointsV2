package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.Modelos.EventoPruebaDanny;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.util.ArrayList;

public class EventoInfo_MD extends AppCompatActivity {

    Boolean usuarioEncontrado = false;
    private View rootView;
    private ImageView evento_info_img;
    private TextView unirse_a_evento;
    private TextView nombre_evento_info;
    private TextView lugar_evento_info;
    private TextView fecha_evento_info;
    private TextView hora_evento_info;
    private Button btn_evento_info;
    private Button unirse_btn;
    private TextView descripcion_evento_info;

    FirebaseStorage firebaseStorageRef = FirebaseStorage.getInstance();
    StorageReference mStorageRef = firebaseStorageRef.getReference();

    FirebaseDatabase mDataRef = FirebaseDatabase.getInstance();
    DatabaseReference participantesRef = mDataRef.getReference();
    String participanteKey;

    private ArrayList<String> listaParticipantes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_info__md);

        rootView = (View) findViewById(R.id.activity_evento_info__md);
        unirse_btn = (Button) findViewById(R.id.unirse_evento_md);
        evento_info_img = (ImageView) findViewById(R.id.evento_info_img);
        nombre_evento_info = (TextView) findViewById(R.id.nombre_evento_info_md);
        lugar_evento_info = (TextView) findViewById(R.id.lugar_evento_info_md);
        fecha_evento_info = (TextView) findViewById(R.id.fecha_evento_info_md);
        hora_evento_info = (TextView) findViewById(R.id.hora_evento_info_md);
        btn_evento_info = (Button) findViewById(R.id.btn_evento_info_md);
        descripcion_evento_info = (TextView) findViewById(R.id.descripcion_evento_info_md);
        unirse_a_evento = (TextView) findViewById(R.id.unirse_evento_md);

        EventoPruebaDanny e = getIntent().getParcelableExtra("PARCELABLE");

        //vamos a la ruta de la imagen

        mStorageRef = mStorageRef.child("eventos/" + e.getImagen());

        participantesRef = participantesRef.child("Eventos")
                .child(e.getNombre().toString()).child("participantes");


        participantesRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String str = dataSnapshot.getValue(String.class).toString();
                if (!usuarioEncontrado && "rita".equals(str)) {
                    unirse_a_evento.setText("No voy a ir");
                    usuarioEncontrado = true;
                    participanteKey = dataSnapshot.getKey();
                }
                listaParticipantes.add(str);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String usuarioBorrado = dataSnapshot.getValue(String.class).toString();
                borrarElementoLista(usuarioBorrado);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        nombre_evento_info.setText(e.getNombre().toString());
        lugar_evento_info.setText(e.getLugar());
        fecha_evento_info.setText(e.getFecha());
        hora_evento_info.setText(e.getHora());
        descripcion_evento_info.setText(e.getDescripcion());

        mStorageRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                evento_info_img.setImageBitmap(bmp);

            }
        });


        btn_evento_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // showParticipantesInfo();
                // Snackbar.make(view,getListaParticipantes(), Snackbar.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), getListaParticipantes(), Toast.LENGTH_LONG).show();

            }
        });

        unirse_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (unirse_a_evento.getText().equals("No voy a ir")) {
                    new AlertDialog.Builder(view.getContext())
                            .setTitle("Cancelar participación")
                            .setMessage("Estás seguro que deseas cancelar tu asistencia al evento?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                   if(isOnlineNet()) {
                                       participantesRef.child(participanteKey).removeValue();
                                       usuarioEncontrado = false;
                                       unirse_a_evento.setText("Unirse");
                                       Snackbar.make(rootView, "Has cancelado tu asistencia correctamente", Snackbar.LENGTH_LONG).show();
                                   }
                                    else Snackbar.make(rootView, "Problemas de conexión, inténtelo más tarde...", Snackbar.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                } else {
                    //implementar en la BDD - unir usuario a evento
                    Snackbar.make(view, "Te has unido al evento correctamente", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void mostrarSnackBar() {

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

    private void borrarElementoLista(String usuario){
        for(int i = 0; i < listaParticipantes.size();i++){
            if(usuario.equals(listaParticipantes.get(i)))
                listaParticipantes.remove(i);
        }
    }

    //Comprobar si tenemos internet en un momento determinado
    public Boolean isOnlineNet() {

        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");

            int val           = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

}
