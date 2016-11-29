package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.example.dannyang27.sportpoints.activities.Modelos.EquipoPruebaDanny;
import com.example.dannyang27.sportpoints.activities.Modelos.EventoPruebaDanny;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by Dannyang27 on 13/11/16.
 */
public class EquipoFragmento extends Fragment {
    Context c;
    FloatingActionButton fab;
    RecyclerView rv;

    DatabaseReference mDataRef;
    DatabaseReference mRefEquipo;
    static String nombreImagenEquipo = "";

    public static final int GALLERY_INTENT_EQUIPO = 2;
    FirebaseStorage firebaseStorageRef = FirebaseStorage.getInstance();
    StorageReference mStorageRef =firebaseStorageRef.getReference();


    //Widgets del Dialogo

    private ImageView imagen;
    private FloatingActionButton fab_dialog;
    private EditText nombre_et;
    private EditText deporte_et;
    private EditText capacidadMaxima_et;
    private Button cancelar_btn;
    private Button crear_btn;



    public static void setNombreImagenEquipo(String nombreImagenEquipo) {
        EquipoFragmento.nombreImagenEquipo = nombreImagenEquipo;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Referencio equipos
        mDataRef =  FirebaseDatabase.getInstance().getReference();
        mRefEquipo = mDataRef.child("Equipos");
        participantes.add("Tecnico");


        View v = inflater.inflate(R.layout.aaa_activity_equipo_fragmento, container, false);
        rv = (RecyclerView) v.findViewById(R.id.rv_id_equipo);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));

        fab = (FloatingActionButton) v.findViewById(R.id.fab_equipo_md);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
               // Toast.makeText(getContext(),"CREAR EQUIPO", Toast.LENGTH_SHORT).show();

                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.aaa_activity_dialog_crear_equipo);
                dialog.show();

                imagen = (ImageView) dialog.findViewById(R.id.imagen_dialog_equipo);
                fab_dialog = (FloatingActionButton) dialog.findViewById(R.id.fab_dialog_equipo);
                nombre_et = (EditText) dialog.findViewById(R.id.nombre_dialog_equipo);
                deporte_et = (EditText) dialog.findViewById(R.id.deporte_dialog_equipo);
                capacidadMaxima_et = (EditText) dialog.findViewById(R.id.capMax_dialog_equipo);
                cancelar_btn = (Button) dialog.findViewById(R.id.cancelarBtn_dialog_equipo);
                crear_btn = (Button) dialog.findViewById(R.id.crearBtn_dialog_equipo);

                crear_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String camposObligatorios="";
                        if(nombre_et.getText().toString().equals("")){
                            camposObligatorios += "Introduzca el nombre\n";
                        }
                        if(deporte_et.getText().toString().equals("")){
                            camposObligatorios += "Introduzca el deporte\n";
                        }
                        if(capacidadMaxima_et.getText().toString().equals("")){
                            camposObligatorios += "Introduzca la capacidad maxima\n";
                        }
                        if(camposObligatorios.length()==0){
                            DatabaseReference mEquiposRef = mDataRef.child("Equipos");

                            ArrayList<String> a = new ArrayList<String>();
                            a.add("Cristiano Ronaldo");
                            a.add("Lionel Messi");
                            EquipoPruebaDanny e = new EquipoPruebaDanny("Dannyang27",
                                    nombre_et.getText().toString(),
                                    deporte_et.getText().toString(),
                                    "DESCRIPCION",
                                    "1",capacidadMaxima_et.getText().toString(),nombreImagenEquipo,a);

                            mEquiposRef.child(nombre_et.getText().toString()).setValue(e);
                            Snackbar.make(view,"Equipo creado", Snackbar.LENGTH_LONG).show();

                            dialog.cancel();


                        }else{
                            Toast.makeText(getContext(), camposObligatorios.substring(0, camposObligatorios.length()-1),Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                cancelar_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar.make(view,"Crear equipo cancelado", Snackbar.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });


                fab_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_PICK);
                        i.setType("image/*");
                        startActivityForResult(i, GALLERY_INTENT_EQUIPO);

                    }
                });

            }
        });

        //RecyclerAdapterEquipo adapter = new RecyclerAdapterEquipo(getContext());
        //adapter.addEquipos();
        //rv.setAdapter(adapter);

        FirebaseRecyclerAdapter<EquipoPruebaDanny, EquipoViewHolder> adapter;
        adapter = new FirebaseRecyclerAdapter<EquipoPruebaDanny, EquipoViewHolder>(EquipoPruebaDanny.class,
                R.layout.aaa_md_equipos,EquipoViewHolder.class, mRefEquipo) {
            @Override
            protected void populateViewHolder(final EquipoViewHolder viewHolder,final EquipoPruebaDanny model, int position) {

                viewHolder.nombreTv.setText(model.getNombre());
                viewHolder.deporteTv.setText(model.getDeporte());
                viewHolder.participantesTv.setText(model.getCapacidadActual() +" / "+model.getCapacidadMaxima());

                String imagenId = model.getImagen();


                /*
                viewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        showInfoEquipo(model);

                    }
                });
                 */
                viewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        showInfoEquipo(model);

                    public boolean onLongClick(View view) {

                        new AlertDialog.Builder(getContext())
                                .setTitle("Delete Entry")
                                .setMessage("Estas seguro que quieres borrar este equipo?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if(mAuth.getCurrentUser().getEmail().equals(model.getCreador()) && isOnlineNet()){
                                            mRefEquipo.child(model.getNombre()).removeValue();
                                            Toast.makeText(getContext(), "Elemento borrado", Toast.LENGTH_LONG).show();
                                        }else {
                                            Toast.makeText(getContext(), "No eres el creador del equipo", Toast.LENGTH_LONG).show();
                                        }


                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(getContext(), "Operacion cancelada", Toast.LENGTH_LONG).show();
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert).show();

                        return true;
                    }
                });




                if(!imagenId.equals("")) {
                    StorageReference equiposRef = mStorageRef.child("equipos/"+imagenId);
                    //Bajar la imagen
                    equiposRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            viewHolder.img.setImageBitmap(bmp);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                }
            }
        };

        rv.setAdapter(adapter);


        return v;
    }

    private void showInfoEquipo(EquipoPruebaDanny model) {
        Intent i = new Intent(getContext(), EquipoInfo_MD.class);
        i.putExtra("PARCELABLE",model);
        startActivity(i);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_INTENT_EQUIPO && resultCode == RESULT_OK) {

            Uri uri = data.getData();
            imagen.setImageURI(uri);
            int num = (int)(Math.random()*1000000 +10);
            String child = num+"";
            StorageReference equipoRefRef = mStorageRef.child("equipos").child(child); //uri.getLastPathSegment(), en el child es el nombre de la imagen
            setNombreImagenEquipo(child);
            equipoRefRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                }
            });


        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        c=context;
    }
}
