package com.example.dannyang27.sportpoints.activities.Equipo;



        import android.app.Dialog;
        import android.content.Intent;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.Toast;

        import com.example.dannyang27.sportpoints.R;

        import com.example.dannyang27.sportpoints.activities.PruebasDanny.CustomRow;
        import com.google.firebase.database.ChildEventListener;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

        import java.util.ArrayList;


public class ListarEquipo extends AppCompatActivity {

    private ListView listar;
    private ArrayAdapter<CustomRow.EquipoParceable> adapter;
    private CustomRow.EquipoItemHolder customAdapter;
    private FloatingActionButton newEquipbtn;
    private EditText filter;

    private ArrayList<CustomRow.EquipoParceable> listaEquipos = new ArrayList<>();


    private DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipo_lista);




        filter = (EditText) findViewById(R.id.filter_id1);
        newEquipbtn = (FloatingActionButton) findViewById(R.id.newEquipbtn);
        listar = (ListView) findViewById(R.id.equipo_listView);
        //adapter = new ArrayAdapter<EventoParcelable>(this, android.R.layout.simple_list_item_1, listaEventos);

        customAdapter = new CustomRow.EquipoItemHolder(this, listaEquipos);

        listar.setAdapter(customAdapter);

        listar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                CustomRow.EquipoParceable e = listaEquipos.get(i);
                String str = e.getNombre().toString();
                Toast.makeText(getApplicationContext(),str ,Toast.LENGTH_SHORT).show();

                showEventInfo(e);
            }
        });


        DatabaseReference eRef = mRef.child("Equipos");
        eRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CustomRow.EquipoParceable e = dataSnapshot.getValue(CustomRow.EquipoParceable.class);
                listaEquipos.add(e);
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


        newEquipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(ListarEquipo.this);
                dialog.setContentView(R.layout.crear_equipo);
                dialog.show();


                EditText nombre_et = (EditText) dialog.findViewById(R.id.nombre_equipo_field);
                //acabar de añadir

                final EditText nombre_et1 = (EditText) dialog.findViewById(R.id.nombre_equipo_field);

                Button cancel_btn = (Button) dialog.findViewById(R.id.cancel_btn);
                Button change_btn = (Button) dialog.findViewById(R.id.cambiar_img_btn);
                Button submit_btn = (Button) dialog.findViewById(R.id.crear_btn);

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
                        CustomRow.EquipoParceable eventoData = new CustomRow.EquipoParceable();

                        eventoData.setNombre(nombre_et1.getText().toString());


                        if(nombre_et1.getText().toString().equals("")) {
                            dialog.cancel();
                        }else {

                            eventRef.child(nombre_et1.getText().toString()).setValue(eventoData);
                        }

                        dialog.cancel();
                    }
                });

                change_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Imagen Cambiada",
                                Toast.LENGTH_SHORT).show();


                        //Submitting events to Firebase
                        DatabaseReference eventRef = mRef.child("Eventos");
                        CustomRow.EquipoParceable eventoData = new CustomRow.EquipoParceable();

                        eventoData.setNombre(nombre_et1.getText().toString());


                        if(nombre_et1.getText().toString().equals("")) {
                            dialog.cancel();
                        }else {

                            eventRef.child(nombre_et1.getText().toString()).setValue(eventoData);
                        }

                        dialog.cancel();
                    }
                });


            }
        });




    }

    private void showEventInfo(CustomRow.EquipoParceable e) {
        Intent i = new Intent(this, EquipoInfo.class);
        i.putExtra("PARCELABLE",e);
        startActivity(i);
    }
}
