package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.EventoConfigView;
import com.example.dannyang27.sportpoints.activities.FireBase;
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
    private TextView participantesTv;
    private Button unirseBtn;
    private DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba_evento_info);

        //References to xml file
        unirseBtn = (Button) findViewById(R.id.joinBtn);
        nombreTv = (TextView) findViewById(R.id.nombreTextView);
        descripcionTv = (TextView) findViewById(R.id.descripcionTextView);
        lugarTv = (TextView) findViewById(R.id.lugarTextView);
        horaTv = (TextView) findViewById(R.id.horaTextView);
        fechaTv = (TextView) findViewById(R.id.fechaTextView);
        participantesTv = (TextView) findViewById(R.id.participantesTextView);

        final EventoParcelable evento = getIntent().getParcelableExtra("PARCELABLE");

        nombreTv.setText(evento.getNombre());
        fechaTv.setText(evento.getFecha());
        lugarTv.setText(evento.getLugar());
        horaTv.setText(evento.getHora());
        descripcionTv.setText(evento.getDescripcion());
        participantesTv.setText(evento.getParticipantes().size() + "/22");

        unirseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Pulsado", Toast.LENGTH_SHORT).show();
                final Dialog dialog = new Dialog(PruebaEventoInfo.this);
                dialog.setContentView(R.layout.join_event_dialog);
                dialog.show();

                final EditText nombre_editText = (EditText) dialog.findViewById(R.id.n_editText);
                final EditText tlf_editText = (EditText) dialog.findViewById(R.id.tlf_editText);
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
                        Toast.makeText(getApplicationContext(), "Submitting: "+nombre_editText
                                .getText()
                                .toString(), Toast.LENGTH_SHORT).show();

                        DatabaseReference eventoRef = mRef.child("Eventos");
                        Participante part = new Participante(nombre_editText.getText().toString(),
                                tlf_editText.getText().toString());

                        eventoRef.child(evento.getNombre().toString()).child("Participantes")
                                .push()
                                .setValue(part);

                        dialog.cancel();
                    }
                });

            }
        });
    }

}
