package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dannyang27.sportpoints.R;

import java.util.ArrayList;

public class VerParticipantesProvisional extends AppCompatActivity {

    private ListView lv;
    private ArrayList<String> participantes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aaaa_activity_ver_participantes_provisional);

        Intent i = getIntent();
        participantes = i.getStringArrayListExtra("listaParticipantes");

        lv = (ListView) findViewById(R.id.listView_participantes);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, participantes);

        lv.setAdapter(adapter);
        }
    }

