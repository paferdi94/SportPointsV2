package com.example.dannyang27.sportpoints.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dannyang27.sportpoints.R;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //private DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
    private String id_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        /*
        //Crear Usuario de prueba
        String id_jug = mRef.child("Usuarios").push().getKey();
        Jugador j = new Jugador(id_jug,"pepe","upv2016","pepe","botella","pepe@gmail.com","dsic",new Date(),new ArrayList<Equipo>());
        Map<String, Object> postValuesJ = j.toMap();
        Map<String, Object> childUpdatesJ = new HashMap<>();
        childUpdatesJ.put("/Usuarios/" + id_jug, postValuesJ);
        mRef.updateChildren(childUpdatesJ);

        //Crear Equipo de prueba
        String id_eq = mRef.child("Equipos").push().getKey();
        Bitmap logo = BitmapFactory.decodeResource(getBaseContext().getResources(), R.mipmap.logo_sport_points);
        Equipo q = new Equipo(id_eq,"UPV",j,logo);
        Map<String, Object> postValuesEq = q.toMap();
        Map<String, Object> childUpdatesEq = new HashMap<>();
        childUpdatesEq.put("/Equipos/" + id_jug, postValuesEq);
        mRef.updateChildren(childUpdatesEq);
        */
        id_usuario = "-KVLiykWMQXr4_aQf8tz";
        //cargar fuentes
        TextView title_txt = (TextView) findViewById(R.id.title_txt);
        title_txt.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/AlfaSlabOne-Regular.ttf"));
    }

    public void onclick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
//            case R.id.signIn_btn:
//                intent = new Intent(this, PerfilAlta.class);
//                break;
            case R.id.login_btn:
                intent = new Intent(this, OpcionesChoser.class);
                intent.putExtra("id_usuario",id_usuario);
                break;
        }
        startActivity(intent);
    }
}


