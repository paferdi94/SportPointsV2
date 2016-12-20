package com.example.dannyang27.sportpoints.activities.Evento;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.util.Log;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Dannyang27 on 13/11/16.
 */
public class EventoFragmento extends Fragment {
    Context c;
    FloatingActionButton fab;
    //FloatingActionButton fab2;
    DatabaseReference mDataRef;
    DatabaseReference mEventoRef;
    static String nombreImagenEvento = "";

    public static final int GALLERY_INTENT = 2;
    FirebaseStorage firebaseStorageRef = FirebaseStorage.getInstance();
    StorageReference mStorageRef = firebaseStorageRef.getReference();

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
        EventoFragmento.nombreImagenEvento = nombreImagen;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataRef = FirebaseDatabase.getInstance().getReference();
        mEventoRef = mDataRef.child("Eventos");
        View v = inflater.inflate(R.layout.aaa_activity_evento_fragmento, container, false);
        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv_id);
        fab = (FloatingActionButton) v.findViewById(R.id.fab_evento_md);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        rootView = (View) v.findViewById(R.id.coordinate);

        mAuth = FirebaseAuth.getInstance();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                final Dialog dialog = new Dialog(getContext());
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
                        String camposObligatorios = "";
                        if (nombre_et.getText().toString().equals("")) {
                            camposObligatorios += "Introduzca el nombre del evento\n";
                        }
                        if (lugar_et.getText().toString().equals("")) {
                            camposObligatorios += "Introduzca el lugar del evento\n";
                        }
                        if (hora_et.getText().toString().equals("")) {
                            camposObligatorios += "Introduzca la hora del evento\n";
                        }
                        if (fecha_et.getText().toString().equals("")) {
                            camposObligatorios += "Introduzca la fecha del evento\n";
                        }
                        DateFormat format = new SimpleDateFormat("d/L/yyyy");
                        try {
                            format.parse(fecha_et.getText().toString());
                        } catch (ParseException e) {
                            camposObligatorios += "Introduzca una fecha correcta (dd/mm/yyyy)\n";
                        }
                        format = new SimpleDateFormat("HH:mm");
                        try {
                            Date hoy = new Date();
                            hoy.setTime(0);
                            hoy.setHours(23);
                            hoy.setMinutes(59);
                            Date hora = format.parse(hora_et.getText().toString());
                            Log.d("SportPoints",hoy.toString());
                            Log.d("SportPoints",hora.toString());
                            if(hora.after(hoy)){
                                throw new Exception();
                            }
                        } catch (ParseException e) {
                            camposObligatorios += "Introduzca una hora correcta (hh:mm)\n";
                        } catch (Exception e){
                            camposObligatorios += "Introduzca una hora correcta (23:59)\n";
                        }
                        if (camposObligatorios.length() == 0) {

                            nombreEv = nombre_et.getText().toString();
                            lugarEv = lugar_et.getText().toString();
                            fechaEv = fecha_et.getText().toString();
                            horaEv = hora_et.getText().toString();

                            /*
                            participantes.add("Pepi");
                            participantes.add("Iban");
                            participantes.add("Guillem");
                            participantes.add("Le Danny");
                             */

                            //mRefEventos.child(nombre_et.getText().toString()).setValue(e);
                            //Snackbar.make(view,"Evento creado", Snackbar.LENGTH_LONG).show();
                            dialog.setContentView(R.layout.aaa_activity_dialog_descripcion);
                            descripcion_et = (EditText) dialog.findViewById(R.id.descripcion_dialog_equipo);
                            crearBtn = (Button) dialog.findViewById(R.id.crear_dialog_equipo);

                            crearBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    DatabaseReference mRefEventos = mDataRef.child("Eventos");
                                    EventoPruebaDanny e = new EventoPruebaDanny(nombreImagenEvento
                                            , nombreEv, lugarEv, horaEv, fechaEv,
                                            descripcion_et.getText().toString(),
                                            mAuth.getCurrentUser().getEmail(), "1", "22", participantes);
                                    //Añadir el creador al evento
                                    e.getParticipantes().add(mAuth.getCurrentUser().getEmail());

                                    mRefEventos.child(nombreEv).setValue(e);
                                    Snackbar.make(view, "Evento creado", Snackbar.LENGTH_LONG).show();
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
                        Snackbar.make(view, "Evento cancelado", Snackbar.LENGTH_LONG).show();
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

        /*
        RecyclerAdapter adapter = new RecyclerAdapter(getContext());
        //adapter.addEventos();
        rv.setAdapter(adapter);
         */

        FirebaseRecyclerAdapter<EventoPruebaDanny, EventoViewHolder> adapter;

        adapter = new FirebaseRecyclerAdapter<EventoPruebaDanny, EventoViewHolder>(EventoPruebaDanny.class,
                R.layout.aaa_md_eventos, EventoViewHolder.class, mEventoRef) {


            @Override
            protected void populateViewHolder(final EventoViewHolder viewHolder, final EventoPruebaDanny model, int position) {

                viewHolder.nombreTv.setText(model.getNombre());
                viewHolder.lugarTv.setText(model.getLugar());
                viewHolder.horaTv.setText(model.getHora());
                viewHolder.fechaTv.setText(model.getFecha());


                //Sacamos el id de la imagen
                String imagenId = model.getImagen();

                viewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showInfoEvento(model);
                    }
                });

                viewHolder.view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        //Toast.makeText(getContext(),model.getImagen(),Toast.LENGTH_LONG).show();
                        mEventoRef = mDataRef.child("Eventos").child(model.getNombre());
                        //Toast.makeText(getContext(),model.getNombre(),Toast.LENGTH_LONG).show();

                        new AlertDialog.Builder(v.getContext())
                                .setTitle("Eliminar Evento")
                                .setMessage("Estás seguro que deseas eliminar el evento?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //if (isOnlineNet()) {
                                            if (model.getAdmin().equals(mAuth.getCurrentUser().getEmail().toString())) {

                                                mEventoRef.removeValue();
                                                Snackbar.make(rootView, "Evento eliminado", Snackbar.LENGTH_LONG).show();
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
                    StorageReference eventosRef = mStorageRef.child("eventos/" + imagenId);
                    //Bajar la imagen
                    eventosRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
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

    public void generateDialog(View view, DatabaseReference ref) {

    }


    public void showInfoEvento(EventoPruebaDanny e) {
        Intent i = new Intent(getContext(), EventoInfo_MD.class);
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
            StorageReference eventoRef = mStorageRef.child("eventos").child(child); //uri.getLastPathSegment(), en el child es el nombre de la imagen
            setNombreImagenEvento(child);
            eventoRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    //setNombreImagenEvento(""); ---> FALLAZO BRO!
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
