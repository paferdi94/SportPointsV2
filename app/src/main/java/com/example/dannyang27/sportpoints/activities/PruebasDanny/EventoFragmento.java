package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;

/**
 * Created by Dannyang27 on 13/11/16.
 */
public class EventoFragmento extends Fragment {
    Context c;
    FloatingActionButton fab;
    FloatingActionButton fab2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.aaa_activity_evento_fragmento, container, false);
        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv_id);
        fab = (FloatingActionButton) v.findViewById(R.id.fab_evento_md);
        fab2 = (FloatingActionButton) v.findViewById(R.id.fab2_evento_md);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        //rv.setLayoutManager(layoutManager);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fab2.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Fab pulsado ", Toast.LENGTH_LONG).show();
            }
        });




        RecyclerAdapter adapter = new RecyclerAdapter(getContext());
        adapter.addEventos();
        rv.setAdapter(adapter);


        return v;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        c= context;
    }


}
