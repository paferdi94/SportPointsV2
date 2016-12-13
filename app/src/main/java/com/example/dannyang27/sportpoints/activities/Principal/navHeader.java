package com.example.dannyang27.sportpoints.activities.Principal;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dannyang27.sportpoints.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;



/**
 * Created by Pablo_Fernandez on 4/12/16.
 */

public class navHeader extends AppCompatActivity {

    private TextView nombre;
    private TextView email;
    private ImageView fotoPerfil;

    private FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth.getCurrentUser();
        setContentView(R.layout.nav_header_pprincipal);

        nombre =  (TextView)findViewById(R.id.nombrePerf);
        email = (TextView) findViewById(R.id.emailPerf);
        fotoPerfil= (ImageView) findViewById(R.id.fotoPerf);

        nombre.setText(mAuth.getCurrentUser().getDisplayName());
        email.setText(mAuth.getCurrentUser().getEmail());
        //fotoPerfil.setImageBitmap(mAuth.getCurrentUser().getPhotoUrl());

    }


}
