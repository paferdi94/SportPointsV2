package com.example.dannyang27.sportpoints.activities;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.Modelos.EventoParcelable;

public class EventoConfigView extends AppCompatActivity {

    private TextView nombreTv;
    private TextView fechaTv;
    private TextView horaTv;
    private TextView descripcionTv;
    private TextView participantes;
    private FloatingActionButton fab;
    private Button unirseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evento_config_view);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        fab = (FloatingActionButton) findViewById(R.id.id_fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Se presionó el FAB", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Log.e("FAB","dasdsadasd");
//                //Toast.makeText(getApplicationContext(), "FAB", Toast.LENGTH_SHORT).show();
//            }
//        });



        unirseBtn = (Button) findViewById(R.id.joinBtn);

        nombreTv = (TextView) findViewById(R.id.nombreTxt);
        fechaTv = (TextView) findViewById(R.id.fechaTxt);
        horaTv = (TextView) findViewById(R.id.horaTxt);
        descripcionTv = (TextView) findViewById(R.id.descripcionTxt);
        participantes = (TextView) findViewById(R.id.participantesTxt);

        final EventoParcelable evento = getIntent().getParcelableExtra("PARCELABLE");

        nombreTv.setText(evento.getNombre());
        fechaTv.setText(evento.getFecha());
        horaTv.setText(evento.getHora());
        descripcionTv.setText(evento.getDescripcion());
        participantes.setText(evento.getParticipantes().size() + "/22");



//        unirseBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Toast.makeText(getApplicationContext(),"Pulsado", Toast.LENGTH_SHORT).show();
//                final Dialog dialog = new Dialog(EventoConfigView.this);
//                //dialog.setTitle("WOW");
//                dialog.setContentView(R.layout.join_event_dialog);
//                dialog.show();
//
//                final EditText nombre_editText = (EditText) dialog.findViewById(R.id.n_editText);
//                EditText tlf_editText = (EditText) dialog.findViewById(R.id.tlf_editText);
//                Button cancel_btn = (Button) dialog.findViewById(R.id.cancel_btn);
//                Button submit_btn = (Button) dialog.findViewById(R.id.submit_btn);
//
//                cancel_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.cancel();
//                    }
//                });
//
//                submit_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getApplicationContext(), "Submitting: "+nombre_editText
//                                .toString(), Toast.LENGTH_SHORT).show();
//                        dialog.cancel();
//                    }
//                });
//
//            }
//        });

     }



}
