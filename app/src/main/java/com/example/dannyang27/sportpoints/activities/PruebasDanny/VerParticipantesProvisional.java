package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dannyang27.sportpoints.R;

import java.util.ArrayList;

public class VerParticipantesProvisional extends AppCompatActivity {

    private Toolbar tb;
    private ListView lv;
    private ArrayList<String> participantes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aaaa_activity_ver_participantes_provisional);

        Intent i = getIntent();
        participantes = i.getStringArrayListExtra("listaParticipantes");


        tb = (Toolbar) findViewById(R.id.tb);
        lv = (ListView) findViewById(R.id.listView_participantes);

        /////////////////////Añadir Toolbar////////////////////////
        tb.setTitle("Listado de participantes");
        setSupportActionBar(tb);
        tb.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //////////////////////////////////////////////////////////

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.custom_textview, participantes);

        lv.setAdapter(adapter);
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
    }

