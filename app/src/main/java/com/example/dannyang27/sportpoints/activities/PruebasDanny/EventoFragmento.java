package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.Modelos.EventoPruebaDanny;
import com.example.dannyang27.sportpoints.activities.Modelos.Participante;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Dannyang27 on 13/11/16.
 */
public class EventoFragmento extends Fragment {
    Context c;
    FloatingActionButton fab;
    //FloatingActionButton fab2;
    DatabaseReference mDataRef;
    static String nombreImagen = "";

    public static final int GALLERY_INTENT = 2;
    FirebaseStorage firebaseStorageRef = FirebaseStorage.getInstance();
    StorageReference mStorageRef =firebaseStorageRef.getReference();


    //Widgets del Dialogo
    private ImageView imagen;
    private FloatingActionButton fab_dialog;
    private EditText nombre_et;
    private EditText lugar_et;
    private EditText fecha_et;
    private EditText hora_et;

    private Button cancelar_btn;
    private Button crear_btn;

    public static String getNombreImagen() {
        return nombreImagen;
    }

    public static void setNombreImagen(String nombreImagen) {
        EventoFragmento.nombreImagen = nombreImagen;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataRef =  FirebaseDatabase.getInstance().getReference();
        View v = inflater.inflate(R.layout.aaa_activity_evento_fragmento, container, false);
        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv_id);
        fab = (FloatingActionButton) v.findViewById(R.id.fab_evento_md);
        //fab2 = (FloatingActionButton) v.findViewById(R.id.fab2_evento_md);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        //rv.setLayoutManager(layoutManager);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fab2.setVisibility(View.VISIBLE);
                //Toast.makeText(getContext(), mDataRef.toString(), Toast.LENGTH_LONG).show();


                /*
                DatabaseReference mRefEventos = mDataRef.child("Eventos");
                ArrayList<Participante> participantes = new ArrayList<Participante>();
                participantes.add(new Participante("X2084394","daskdjhsa@dkhaksd.com","12/1/1","4564564","dasdasdas"));
                EventoPruebaDanny e = new EventoPruebaDanny("","DANNY","Denia","14:30h","24/02/17","Descripcion lalala","dannyang27","1","22", participantes);
                mRefEventos.child(e.getNombre()).setValue(e);
                 */

               final  Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.aaa_activity_dialog_crear_evento);
                dialog.show();

                imagen = (ImageView) dialog.findViewById(R.id.imagen_dialog_evento);
                fab_dialog = (FloatingActionButton) dialog.findViewById(R.id.fab_dialog_evento);
                nombre_et = (EditText) dialog.findViewById(R.id.nombre_dialog_evento);
                lugar_et = (EditText) dialog.findViewById(R.id.lugar_dialog_evento);
                hora_et = (EditText) dialog.findViewById(R.id.hora_dialog_evento);
                fecha_et = (EditText) dialog.findViewById(R.id.fecha_dialog_evento);
                cancelar_btn = (Button) dialog.findViewById(R.id.cancelarBtn_dialog_evento);
                crear_btn = (Button) dialog.findViewById(R.id.crearBtn_dialog_evento);
                crear_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String camposObligatorios="";
                       if(nombre_et.getText().toString().equals("")){
                           camposObligatorios += "Introduzca el nombre del evento\n";
                       }
                       if(lugar_et.getText().toString().equals("")){
                           camposObligatorios += "Introduzca el lugar del evento\n";
                       }
                       if(hora_et.getText().toString().equals("")){
                            camposObligatorios += "Introduzca la hora del evento\n";
                        }
                       if(fecha_et.getText().toString().equals("")){
                           camposObligatorios += "Introduzca la fecha del evento\n";
                       }

                        if(camposObligatorios.length()==0){
                            DatabaseReference mRefEventos = mDataRef.child("Eventos");
                            ArrayList<String> participantes = new ArrayList<String>();
                            participantes.add("Pepi");
                            participantes.add("Iban");
                            participantes.add("Guillem");
                            participantes.add("Le Danny");
                            EventoPruebaDanny e = new EventoPruebaDanny(getNombreImagen(),
                                    nombre_et.getText().toString(),
                                    lugar_et.getText().toString(),
                                    hora_et.getText().toString(),
                                    fecha_et.getText().toString(),
                                    "Descripcion lalala","dannyang27","1","22", participantes);
                            mRefEventos.child(nombre_et.getText().toString()).setValue(e);
                            Toast.makeText(dialog.getContext(), "Evento creado ", Toast.LENGTH_LONG).show();
                            dialog.cancel();
                        }else{
                            Toast.makeText(getContext(), camposObligatorios.substring(0, camposObligatorios.length()-1),Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                cancelar_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(dialog.getContext(), "Evento cancelado", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });

                fab_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_PICK);
                        i.setType("image/*");
                        startActivityForResult(i, GALLERY_INTENT);

                    }
                });
            }
        });

        RecyclerAdapter adapter = new RecyclerAdapter(getContext());
        adapter.addEventos();
        rv.setAdapter(adapter);
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {

            Uri uri = data.getData();
            imagen.setImageURI(uri);
            //Toast.makeText(getContext(), uri.getScheme(), Toast.LENGTH_LONG).show();

            int num = (int)(Math.random()*100 +10);
            String child = num+"";
            StorageReference eventoRef = mStorageRef.child("eventos").child(child); //uri.getLastPathSegment(), en el child es el nombre de la imagen
            setNombreImagen(child);
            eventoRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), "Imagen subida", Toast.LENGTH_LONG).show();
                }
            });


        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        c= context;
    }












}
