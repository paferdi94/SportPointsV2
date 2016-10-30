package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.EventoConfigView;
import com.example.dannyang27.sportpoints.activities.Modelos.Evento;
import com.example.dannyang27.sportpoints.activities.Modelos.EventoParcelable;
import com.example.dannyang27.sportpoints.activities.OpcionesChoser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class PruebaListarEvento extends AppCompatActivity {

    private ListView lv;
    private ArrayAdapter<EventoParcelable> adapter;
    private CustomRow customAdapter;
    private String [] eventos = {"Evento 1","Evento 2","Evento 3","Evento 4","Evento 5"};
<<<<<<< HEAD
    private FloatingActionButton newEventBtn;
    private EditText filter;
=======
    private Button newEventBtn;
   // private EditText filter;
>>>>>>> Dannyang27/master

    private ArrayList<EventoParcelable> listaEventos = new ArrayList<>();

    private DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba_listar_evento);

<<<<<<< HEAD
        //Creacion de EventosParcelables

//        EventoParcelable ep1 = new EventoParcelable();
//        EventoParcelable ep2 = new EventoParcelable();
//
//        ep1.setNombre("DENIA-VALENCIA");
//        ep1.setHora("14:30");
//        ep1.setFecha("27-09-2017");
//        ep1.setLugar("Denia");
//        ep1.setDescripcion("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
//                "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
//                "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
//                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu " +
//                "fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa" +
//                " qui officia deserunt mollit anim id est laborum.");
//        ep2.setNombre("DENIA-MADRID");
//        ep2.setHora("16:30");
//        ep2.setFecha("21-10-2017");
//        ep2.setLugar("Madrid");
//        ep2.setDescripcion("At vero eos et accusamus et iusto odio dignissimos ducimus qui " +
//                "blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas " +
//                "molestias excepturi sint occaecati cupiditate non provident, similique sunt in " +
//                "culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. " +
//                "Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, " +
//                "cum soluta nobis est eligendi optio cumque nihil impedit");
//
//        //fin creacion eventosParcelables
//
//        listaEventos.add(ep1);
//        listaEventos.add(ep2);

        filter = (EditText) findViewById(R.id.filter_id);
        newEventBtn = (FloatingActionButton) findViewById(R.id.id_new_event);
=======
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_id_2);
        toolbar.setTitle("LISTADO DE EVENTOS");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //filter = (EditText) findViewById(R.id.filter_id);
        newEventBtn = (Button) findViewById(R.id.id_new_event);
>>>>>>> Dannyang27/master
        lv = (ListView) findViewById(R.id.id_lv);
        //adapter = new ArrayAdapter<EventoParcelable>(this, android.R.layout.simple_list_item_1, listaEventos);

        customAdapter = new CustomRow(this, listaEventos);

        lv.setAdapter(customAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                EventoParcelable e = listaEventos.get(i);
                String str = e.getNombre().toString();
                //Toast.makeText(getApplicationContext(),str ,Toast.LENGTH_SHORT).show();

                showEventInfo(e);
            }
        });

        DatabaseReference eRef = mRef.child("Eventos");
        eRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                EventoParcelable e = dataSnapshot.getValue(EventoParcelable.class);
                listaEventos.add(e);
                customAdapter.notifyDataSetChanged();
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


        /*
        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               customAdapter.getFilter().filter(charSequence);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
         */



        newEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(PruebaListarEvento.this);
                dialog.setContentView(R.layout.create_event_dialog);
                dialog.show();

                final EditText nombre_et = (EditText) dialog.findViewById(R.id.nombre_id);
                final EditText descripcion_et = (EditText) dialog.findViewById(R.id.descripcion_id);
                final EditText lugar_et = (EditText) dialog.findViewById(R.id.lugar_id);
                final EditText hora_et = (EditText) dialog.findViewById(R.id.hora_id);
                final EditText fecha_et = (EditText) dialog.findViewById(R.id.fecha_id);

                Button cancel_btn = (Button) dialog.findViewById(R.id.cancelar_buttonId);
                Button submit_btn = (Button) dialog.findViewById(R.id.submit_buttonId);

                cancel_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                submit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Evento Creado",
                                Toast.LENGTH_SHORT).show();

                        //Submitting events to Firebase
                        DatabaseReference eventRef = mRef.child("Eventos");
                        EventoParcelable eventoData = new EventoParcelable();

                        eventoData.setNombre(nombre_et.getText().toString());
                        eventoData.setHora(hora_et.getText().toString());
                        eventoData.setFecha(fecha_et.getText().toString());
                        eventoData.setLugar(lugar_et.getText().toString());
                        eventoData.setDescripcion(descripcion_et.getText().toString());
                        eventoData.setCapacidadMaxima(22);
                        eventoData.setCapacidadActual(eventoData.getParticipantes().size());

                        if(nombre_et.getText().toString().equals("")) {
                            dialog.cancel();
                        }else {

                            eventRef.child(nombre_et.getText().toString()).setValue(eventoData);
                        }

                        dialog.cancel();
                    }
                });
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
    private void showEventInfo(EventoParcelable e) {
        Intent i = new Intent(this, PruebaEventoInfo.class);
        i.putExtra("PARCELABLE",e);
        startActivity(i);
    }
}
