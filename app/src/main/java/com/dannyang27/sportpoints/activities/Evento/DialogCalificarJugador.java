package com.dannyang27.sportpoints.activities.Evento;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.dannyang27.sportpoints.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.dannyang27.sportpoints.activities.Entidades.Jugador;

import java.text.DecimalFormat;

public class DialogCalificarJugador extends AppCompatActivity {

    private Button btnCancelar;
    private Button btnCalificar;
    private RatingBar ratingBar;
    private TextView txtRating;
    private TextView txtPorcentaje;
    private TextView txtNumValoraciones;
    private double valActual;
    private int numValoraciones;
    Jugador jugadorReferencia;
    Boolean valoracion = false;
    DecimalFormat df = new DecimalFormat("0.00");
    String key;
    FirebaseDatabase mDataRef = FirebaseDatabase.getInstance();
    DatabaseReference usuarioRef = mDataRef.getReference().child("Usuarios");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_calificar_jugador);

        btnCalificar = (Button) findViewById(R.id.btnCalificar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        txtRating = (TextView) findViewById(R.id.txtValoracion);
        txtPorcentaje = (TextView) findViewById(R.id.txtPorcentaje);
        txtNumValoraciones = (TextView) findViewById(R.id.numValoraciones);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        //Get the bundle
        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        final String email = bundle.getString("email").trim();
        txtRating.setText(txtRating.getText() + "" + email);

        usuarioRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Jugador j = dataSnapshot.getValue(Jugador.class);
                if (j.getEmail().equals(email)) {
                    jugadorReferencia = j;
                    key = dataSnapshot.getKey();
                    txtPorcentaje.setText(df.format(((j.getValoracion() * 100) / 5)) + "%");
                    txtNumValoraciones.setText("(" + j.getNumValoraciones() + " valoraciones de los usuarios)");
                    //valActual = j.getValoracion();
                    //numValoraciones = j.getNumValoraciones();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                jugadorReferencia = dataSnapshot.getValue(Jugador.class);
                txtPorcentaje.setText(df.format(((jugadorReferencia.getValoracion() * 100) / 5)) + "%");
                txtNumValoraciones.setText("(" + jugadorReferencia.getNumValoraciones() + " valoraciones de los usuarios)");

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

        btnCalificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (!valoracion) {
                    if (isOnlineNet()) {
                        valActual = ((jugadorReferencia.getValoracion() * jugadorReferencia.getNumValoraciones()) + ratingBar.getRating()) / (jugadorReferencia.getNumValoraciones() + 1);
                        jugadorReferencia.setValoracion(valActual);
                        jugadorReferencia.setNumValoraciones(jugadorReferencia.getNumValoraciones() + 1);
                        usuarioRef.child(key).setValue(jugadorReferencia);
                        valoracion = true;
                        Snackbar.make(view, "Valoración enviada correctamente", Snackbar.LENGTH_LONG).show();
                    }else {
                        Snackbar.make(view, "No hay conexión a internet, inténtelo de nuevo más tarde...", Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    Snackbar.make(view, "Ya has valorado a este usuario", Snackbar.LENGTH_LONG).show();
                }

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                finish();
            }
        });
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