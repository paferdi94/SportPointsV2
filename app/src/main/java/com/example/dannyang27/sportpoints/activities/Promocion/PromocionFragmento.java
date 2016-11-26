package com.example.dannyang27.sportpoints.activities.Promocion;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;


/**
 * Created by Pablo_Fernandez on 23/11/16.
 */

public class PromocionFragmento extends Fragment {
    Context c;
    FloatingActionButton fab;
    RecyclerView rv;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.aaa_activity_promocion_fragmento, container, false);
        rv = (RecyclerView) v.findViewById(R.id.rv_id_equipo);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));


        fab = (FloatingActionButton) v.findViewById(R.id.fab_equipo_md);

        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"CREAR PROMOCION", Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerAdapterPromocion adapter = new RecyclerAdapterPromocion(getContext());
        adapter.addPromocion();
        rv.setAdapter(adapter);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        c=context;
    }
}
