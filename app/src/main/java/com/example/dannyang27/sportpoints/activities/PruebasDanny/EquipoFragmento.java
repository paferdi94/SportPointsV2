package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by Dannyang27 on 13/11/16.
 */
public class EquipoFragmento extends Fragment {
    Context c;
    FloatingActionButton fab;
    RecyclerView rv;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.aaa_activity_equipo_fragmento, container, false);
        rv = (RecyclerView) v.findViewById(R.id.rv_id_equipo);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));


        fab = (FloatingActionButton) v.findViewById(R.id.fab_equipo_md);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"CREAR EQUIPO", Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerAdapterEquipo adapter = new RecyclerAdapterEquipo(getContext());
        adapter.addEquipos();
        rv.setAdapter(adapter);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        c=context;
    }
}
