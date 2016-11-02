package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.EventoConfigView;
import com.example.dannyang27.sportpoints.activities.Modelos.EventoParcelable;
import com.example.dannyang27.sportpoints.activities.Modelos.Participante;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PruebaEventoInfo extends AppCompatActivity {

    private TextView nombreTv;
    private TextView descripcionTv;
    private TextView lugarTv;
    private TextView horaTv;
    private TextView fechaTv;
    //private TextView participantesTv;
    private Button unirseBtn;
    private Button verParticipantes;
    private EventoParcelable evento;
    private DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
    private EditText nombre_editText;
    private EditText tlf_editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba_evento_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_id_3);
        toolbar.setTitle("INFORMACION EVENTO");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //References to xml file
        //verParticipantes = (Button) findViewById(R.id.verP_id);
        unirseBtn = (Button) findViewById(R.id.joinBtn);
        nombreTv = (TextView) findViewById(R.id.nombreTextView);
        descripcionTv = (TextView) findViewById(R.id.descripcionTextView);
        lugarTv = (TextView) findViewById(R.id.lugarTextView);
        horaTv = (TextView) findViewById(R.id.horaTextView);
        fechaTv = (TextView) findViewById(R.id.fechaTextView);
        //participantesTv = (TextView) findViewById(R.id.participantesTextView);

        evento = getIntent().getParcelableExtra("PARCELABLE");

        nombreTv.setText(evento.getNombre());
        fechaTv.setText(evento.getFecha());
        lugarTv.setText(evento.getLugar());
        horaTv.setText(evento.getHora());
        descripcionTv.setText(evento.getDescripcion());
        //participantesTv.setText(evento.getParticipantes().size() + "/22");

//        verParticipantes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goListarParticipantes(evento);
//            }
//        });

        unirseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(getApplicationContext(),"Pulsado", Toast.LENGTH_SHORT).show();
                final Dialog dialog = new Dialog(PruebaEventoInfo.this);
                dialog.setContentView(R.layout.join_event_dialog);
                dialog.show();

                nombre_editText = (EditText) dialog.findViewById(R.id.n_editText);
                tlf_editText = (EditText) dialog.findViewById(R.id.tlf_editText);
                Button cancel_btn = (Button) dialog.findViewById(R.id.cancel_btn);
                Button submit_btn = (Button) dialog.findViewById(R.id.submit_btn);

                cancel_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });

                submit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String check = checkUnirseData();
                        if (check.equals("")) {
                            Toast.makeText(getApplicationContext(), "Usuario añadido",
                                    Toast.LENGTH_SHORT).show();

                            DatabaseReference eventoRef = mRef.child("Eventos");
                            Participante part = new Participante(nombre_editText.getText().toString(),
                                    tlf_editText.getText().toString());


                            eventoRef.child(evento.getNombre().toString()).child("Participantes")
                                    .push()
                                    .setValue(part);
                            dialog.cancel();

                        } else {
                            Toast.makeText(getApplicationContext(), check,
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
    }

    private String checkUnirseData() {
        String err = "";
        if (nombre_editText.getText().toString().equals(""))
            err += "El campo nombre es obligatorio\n";
        if (tlf_editText.getText().toString().equals(""))
            err += "El campo teléfono es obligatorio";
        return err;
    }


    private void goListarParticipantes(EventoParcelable e) {
        Intent i = new Intent(this, ListarParticipantes.class);
        i.putExtra("PARCELABLE", e);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Añadir icono al actionBar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.info_participantes_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.info_participantes:
                goListarParticipantes(evento);
                return true;
            case android.R.id.home:
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
