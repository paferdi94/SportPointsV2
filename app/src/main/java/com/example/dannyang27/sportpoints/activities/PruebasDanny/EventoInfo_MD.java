package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;

import org.w3c.dom.Text;

public class EventoInfo_MD extends AppCompatActivity {

    private ImageView evento_info_img;
    private TextView nombre_evento_info;
    private TextView lugar_evento_info;
    private TextView fecha_evento_info;
    private TextView hora_evento_info;
    private Button btn_evento_info;
    private TextView descripcion_evento_info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_info__md);

        evento_info_img = (ImageView) findViewById(R.id.evento_info_img);
        nombre_evento_info = (TextView) findViewById(R.id.nombre_evento_info_md);
        lugar_evento_info = (TextView) findViewById(R.id.lugar_evento_info_md);
        fecha_evento_info = (TextView) findViewById(R.id.fecha_evento_info_md);
        hora_evento_info = (TextView) findViewById(R.id.hora_evento_info_md);
        btn_evento_info = (Button) findViewById(R.id.btn_evento_info_md);
        descripcion_evento_info = (TextView) findViewById(R.id.descripcion_evento_info_md);

        evento_info_img.setImageResource(R.drawable.mestalla2);
        nombre_evento_info.setText("VALENCIA C.F vs LEVANTE C.D");
        lugar_evento_info.setText("Mestalla");
        fecha_evento_info.setText("Miercoles, Septiembre 2017");
        hora_evento_info.setText("20:00h");
        descripcion_evento_info.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad " +
                "minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea " +
                "commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse" +
                " cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non" +
                " proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");


        btn_evento_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showParticipantesInfo();
            }
        });


    }

    private void showParticipantesInfo() {
        Intent i = new Intent(this, PruebaListarParticipantes.class);
        startActivity(i);
    }
}
