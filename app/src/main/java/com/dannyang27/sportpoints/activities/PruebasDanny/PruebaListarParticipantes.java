package com.dannyang27.sportpoints.activities.PruebasDanny;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dannyang27.sportpoints.R;

public class PruebaListarParticipantes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_listar_participantes);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView_participantes);
        rv.setLayoutManager(new LinearLayoutManager(this));

        RecyclerAdapterParticipantes adapter = new RecyclerAdapterParticipantes();
        adapter.addParticipantes();
        rv.setAdapter(adapter);
    }
}
