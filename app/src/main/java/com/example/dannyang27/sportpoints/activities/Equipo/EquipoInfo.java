package com.example.dannyang27.sportpoints.activities.Equipo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.PruebasDanny.CustomRowEvento;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EquipoInfo extends AppCompatActivity {

    private TextView nom_eq;
    private ImageView imageView3;
    private Button union;

    private DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipo_info);

        nom_eq=(TextView) findViewById(R.id.nom_eq);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        union = (Button) findViewById(R.id.union);

        final EquipoParceable equipo = getIntent().getParcelableExtra("PARCELABLE");

        nom_eq.setText(equipo.getNom());
        //imageView3.setImageBitmap(equipo.getLogo());


        union.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //añadir funcion meter su nombre al equipo

            }
        });

    }


}

