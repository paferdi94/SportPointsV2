package com.example.dannyang27.sportpoints.activities.Promocion;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.lang.annotation.Retention;

import static android.app.Activity.RESULT_OK;


/**
 * Created by Pablo_Fernandez on 23/11/16.
 */

public class PromocionFragmento extends Fragment {
    Context c;
    FloatingActionButton fab;
    RecyclerView rv;
    DatabaseReference mDataRef;
    DatabaseReference mPromoRef;
    private FirebaseAuth mAuth;
    static String nombreImagenPromo = "";

    public static final int GALLERY_INTENT =2;
    FirebaseStorage firebaseStorageRef = FirebaseStorage.getInstance();
    StorageReference mStorageRef =firebaseStorageRef.getReference();

    //Widgets del Dialogo Promo

    private ImageView imagen;
    private FloatingActionButton fab_dialog;
    private EditText nombre_et;
    private EditText lugar_et;
    private EditText fecha_In;
    private EditText fecha_F;
    private Button cancelar_btn;
    private Button crear_btn;

    private String nombrePm;
    private String fechaPmInit;
    private String fechaPmFin;
    private String lugarPm;
    private String descripcion;


    private EditText descripcion_et;
    private Button crearBtn;

    public static String getNombreImagenPromo() {
        return nombreImagenPromo;
    }

    public static void setNombreImagenEvento(String nombreImagen) {
        PromocionFragmento.nombreImagenPromo = nombreImagen;
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mDataRef = FirebaseDatabase.getInstance().getReference();
        mPromoRef = mDataRef.child("Promociones");
        View v = inflater.inflate(R.layout.aaa_activity_promocion_fragmento, container, false);
        rv = (RecyclerView) v.findViewById(R.id.rv_id_promo);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        mAuth = FirebaseAuth.getInstance();

        fab = (FloatingActionButton) v.findViewById(R.id.fab_promo_md); // Boton Floating CrearPromocion

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.aaa_activity_dialog_crear_promocion);
                dialog.show();

                imagen = (ImageView) dialog.findViewById(R.id.imagen_dialog_promocion);
                fab_dialog = (FloatingActionButton) dialog.findViewById(R.id.fab_dialog_promocion);
                nombre_et = (EditText) dialog.findViewById(R.id.nombre_dialog_promocion);
                lugar_et = (EditText) dialog.findViewById(R.id.lugar_dialog_promocion);
                fecha_In = (EditText) dialog.findViewById(R.id.fecha_Init_promocion);
                fecha_F = (EditText) dialog.findViewById(R.id.fecha_fin);
                cancelar_btn = (Button) dialog.findViewById(R.id.cancelarBtn_dialog_promo);
                crear_btn = (Button) dialog.findViewById(R.id.crearBtn_dialog_promo);
                crear_btn.setOnClickListener(new View.OnClickListener() {


                    public void onClick(View v) {
                        String camposObligatorios = "";
                        if (nombre_et.getText().toString().equals("")) {
                            camposObligatorios += "Introduzca el nombre de la promoción\n";
                        }
                        if (lugar_et.getText().toString().equals("")) {
                            camposObligatorios += "Introduzca el lugar de la promoción\n";
                        }

                        if (fecha_In.getText().toString().equals("")) {
                            camposObligatorios += "Introduzca la fecha de inicio de la promoción\n";
                        }
                        if (fecha_F.getText().toString().equals("")) {
                            camposObligatorios += "Introduzca la fecha de inicio de la promoción\n";
                        }

                        if (camposObligatorios.length() == 0) {

                            nombrePm = nombre_et.getText().toString();
                            lugarPm = lugar_et.getText().toString();
                            fechaPmInit = fecha_In.getText().toString();
                            fechaPmFin = fecha_F.getText().toString();


                            dialog.setContentView(R.layout.aaa_activity_dialog_descripcion);
                            descripcion_et = (EditText) dialog.findViewById(R.id.descripcion_dialog_equipo);
                            crearBtn = (Button) dialog.findViewById(R.id.crear_dialog_equipo);

                            crearBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    DatabaseReference mRefPromo = mDataRef.child("Promociones");
                                    PromocionParceable e = new PromocionParceable(getNombreImagenPromo(), nombrePm, lugarPm, fechaPmInit, fechaPmFin, descripcion_et.getText().toString(), mAuth.getCurrentUser().getEmail());

                                    String auth= mAuth.getCurrentUser().getEmail();

                                    mRefPromo.child(nombrePm).setValue(e);
                                    Snackbar.make(view, "Promocion Creada", Snackbar.LENGTH_LONG).show();
                                    dialog.cancel();
                                }
                            });
                        } else {
                            Toast.makeText(getContext(), camposObligatorios.substring(0, camposObligatorios.length() - 1), Toast.LENGTH_SHORT).show();
                        }
                    }


                });
                cancelar_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar.make(view, "Promocion cancelada", Snackbar.LENGTH_LONG).show();
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

        FirebaseRecyclerAdapter<PromocionParceable, PromocionViewHolder> adapter;

        adapter = new FirebaseRecyclerAdapter<PromocionParceable, PromocionViewHolder>(PromocionParceable.class, R.layout.aaa_md_promociones, PromocionViewHolder.class, mPromoRef) {

            @Override
            protected void populateViewHolder(final PromocionViewHolder viewHolder, final PromocionParceable model, int position) {

                viewHolder.nombrePm.setText(model.getNombre());
                viewHolder.lugarPm.setText(model.getLugar());
                viewHolder.fechaIni.setText(model.getFechaIn());
                viewHolder.fechaFin.setText(model.getFechaF());
                String imagenId = model.getImagen();

                viewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        showInfoPromo(model);
                    }
                });

                //Para borrar promocion
                viewHolder.view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {

                        new AlertDialog.Builder(getContext())
                                .setTitle("Eliminar Promocion")
                                .setMessage("¿Estas seguro que quieres borrar esta promoción?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if(mAuth.getCurrentUser().getEmail().equals(model.getCreador()) && isOnlineNet()){
                                            mPromoRef.child(model.getNombre()).removeValue();
                                            Toast.makeText(getContext(), "Promoción borrada", Toast.LENGTH_LONG).show();
                                        }else {
                                            Toast.makeText(getContext(), "No eres el creador de la promoción", Toast.LENGTH_LONG).show();
                                        }


                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(getContext(), "Operacion cancelada", Toast.LENGTH_LONG).show();
                                    }
                                }).show();


                        return true;
                    }
                });

                if (!imagenId.equals("")) {
                    FirebaseStorage storage = FirebaseStorage.getInstance();

                    StorageReference storageRef = storage.getReferenceFromUrl("gs://sport-points-7f5e0.appspot.com/");
                    StorageReference imagesRef = storageRef.child("promociones/"+ imagenId);
                    //StorageReference promosRef = mStorageRef.child("promociones/" + imagenId);
                    //Bajar la imagen
                    imagesRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            viewHolder.img.setImageBitmap(bmp);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Snackbar.make(getView(), "No se han cargado todas las fotos", Snackbar.LENGTH_LONG).show();


                        }
                    });

                }

            }
        };
        rv.setAdapter(adapter);
        return v;
    }


    public void showInfoPromo(PromocionParceable e){
        Intent i = new Intent(getContext(), PromoInfo_MD.class);
        i.putExtra("PARCELABLE",e);
        startActivity(i);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {

            Uri uri = data.getData();
            imagen.setImageURI(uri);
            int num = (int)(Math.random()*1000000 +10);
            String child = num+"";
            StorageReference eventoRef = mStorageRef.child("promociones").child(child); //uri.getLastPathSegment(), en el child es el nombre de la imagen
            setNombreImagenEvento(child);
            eventoRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    setNombreImagenEvento("");
                }
            });


        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        c= context;
    }

    //Comprobar si tenemos internet en un momento determinado
    public Boolean isOnlineNet() {

        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");
            int val = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}
