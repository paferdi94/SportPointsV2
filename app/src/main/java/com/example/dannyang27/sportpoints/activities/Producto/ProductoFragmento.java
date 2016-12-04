package com.example.dannyang27.sportpoints.activities.Producto;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.dannyang27.sportpoints.activities.Evento.EventoFragmento;
import com.example.dannyang27.sportpoints.activities.Evento.EventoInfo_MD;
import com.example.dannyang27.sportpoints.activities.Evento.EventoPruebaDanny;
import com.example.dannyang27.sportpoints.activities.Evento.EventoViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class ProductoFragmento extends Fragment {

    Context c;
    DatabaseReference mDataRef;
    DatabaseReference mProductoRef;
    static String nombreImagenEvento = "";

    public static final int GALLERY_INTENT = 2;
    FirebaseStorage firebaseStorageRef = FirebaseStorage.getInstance();
    StorageReference mStorageRef = firebaseStorageRef.getReference();

    private FloatingActionButton fab;

    //Widgets del Dialogo
    private ImageView imagen;
    private FloatingActionButton fab_dialog;
    private EditText nombre_et;
    private EditText lugar_et;
    private EditText fecha_et;
    private EditText hora_et;
    private Button cancelar_btn;
    private Button crear_btn;
    private String nombreEv;
    private String fechaEv;
    private String lugarEv;
    private String horaEv;
    private String descripcion;
    private ArrayList<String> participantes = new ArrayList<>();

    private EditText descripcion_et;
    private Button crearBtn;
    View rootView;

    private FirebaseAuth mAuth;

    public static String getNombreImagenEvento() {
        return nombreImagenEvento;
    }

    public static void setNombreImagenEvento(String nombreImagen) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataRef = FirebaseDatabase.getInstance().getReference();
        mProductoRef = mDataRef.child("Productos");
        View v = inflater.inflate(R.layout.aaa_activity_producto_fragmento, container, false);
        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv_id_2);
        fab = (FloatingActionButton) v.findViewById(R.id.fab_productos_md);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        rootView = (View) v.findViewById(R.id.coordinate);
        mAuth = FirebaseAuth.getInstance();


        FirebaseRecyclerAdapter<ProductoPruebaDanny, ProductoViewHolder> adapter;


        adapter = new FirebaseRecyclerAdapter<ProductoPruebaDanny, ProductoViewHolder>(ProductoPruebaDanny.class,
                R.layout.aaa_md_productos, ProductoViewHolder.class, mProductoRef) {


            @Override
            protected void populateViewHolder(final ProductoViewHolder viewHolder, final ProductoPruebaDanny model, int position) {

                viewHolder.nombrePm.setText(model.getNombre());
                viewHolder.precioPm.setText(model.getPrecio());

                //Sacamos el id de la imagen
                String imagenId = model.getImagen();

                viewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showInfoProducto(model);
                    }
                });

                viewHolder.view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        //Toast.makeText(getContext(),model.getImagen(),Toast.LENGTH_LONG).show();
                        mProductoRef = mDataRef.child("Eventos").child(model.getNombre());
                        //Toast.makeText(getContext(),model.getNombre(),Toast.LENGTH_LONG).show();

                        new AlertDialog.Builder(v.getContext())
                                .setTitle("Eliminar Evento")
                                .setMessage("Estás seguro que deseas eliminar el evento?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //if (isOnlineNet()) {
                                        if (model.getAutor().equals(mAuth.getCurrentUser().getEmail().toString())) {

                                            mProductoRef.removeValue();
                                            Snackbar.make(rootView, "Producto eliminado", Snackbar.LENGTH_LONG).show();
                                            dialog.cancel();
                                        } else {
                                            Snackbar.make(rootView, "Solo el creador puede eliminar un evento", Snackbar.LENGTH_LONG).show();
                                        }
                                        // } else
                                        //    Snackbar.make(rootView, "Problemas de conexión, inténtelo más tarde...", Snackbar.LENGTH_LONG).show();
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                                .show();

                        return true;
                    }
                });


                if (!imagenId.equals("")) {
                    StorageReference productosRef = mStorageRef.child("productos/" + imagenId);
                    //Bajar la imagen
                    productosRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
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

    public void showInfoProducto(ProductoPruebaDanny e) {
        Intent i = new Intent(getContext(), ProductoInfo_MD.class);
        i.putExtra("PARCELABLE", e);
        startActivity(i);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {

            Uri uri = data.getData();
            imagen.setImageURI(uri);
            int num = (int) (Math.random() * 1000000 + 10);
            String child = num + "";
            StorageReference eventoRef = mStorageRef.child("productos").child(child); //uri.getLastPathSegment(), en el child es el nombre de la imagen
            setNombreImagenEvento(child);
            eventoRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                }
            });
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        c = context;
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
