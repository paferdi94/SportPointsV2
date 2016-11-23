package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;

import org.w3c.dom.Text;


public class EquipoInfo_MD extends AppCompatActivity {

    private ImageView imagenEquipo;
    private TextView nombreTxt;
    private TextView deporteTxt;
    private TextView participantesTxt;
    private Button verPartBtn;
    private Button unirseBtn;
    private TextView descripcionTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo_info__md);

        nombreTxt = (TextView) findViewById(R.id.nombre_equipo_info_md);
        imagenEquipo = (ImageView) findViewById(R.id.image_equipo_info_md);
        deporteTxt = (TextView) findViewById(R.id.deporte_equipo_info_md);
        participantesTxt = (TextView) findViewById(R.id.participantes_equipo_info_md);
        verPartBtn = (Button) findViewById(R.id.verParticipantes_equipo_info_md);
        unirseBtn = (Button) findViewById(R.id.unirse_equipo_info_md);
        descripcionTxt = (TextView) findViewById(R.id.descripcion_equipo_info_md);


        imagenEquipo.setImageResource(R.drawable.denia_logo);
        nombreTxt.setText("C.D.Denia");
        deporteTxt.setText("Futbol");
        participantesTxt.setText("0/11");
        descripcionTxt.setText("At vero eos et accusamus et iusto odio dignissimos ducimus qui" +
                " blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas" +
                " molestias excepturi sint occaecati cupiditate non provident, similique sunt in" +
                " culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et" +
                " harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum" +
                " soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime " +
                "placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. " +
                "Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe" +
                " eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque " +
                "earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus" +
                " maiores alias consequatur aut perferendis doloribus asperiores repellat.");

    verPartBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           showParticipantesInfo();
        }
    });

        unirseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"Te has unido al equipo correctamente", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void showParticipantesInfo() {
        Intent i = new Intent(this, PruebaListarParticipantes.class);
        startActivity(i);
    }




}
