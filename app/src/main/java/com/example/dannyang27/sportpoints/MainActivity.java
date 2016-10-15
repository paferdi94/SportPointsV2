package com.example.dannyang27.sportpoints;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    private Button altaButton;
    private  Button registroButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        registroButton = (Button) findViewById(R.id.registro_button);
        altaButton = (Button) findViewById(R.id.alta_button);

        registroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registro();
            }
        });

        altaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alta();
            }
        });



    }



    public void alta(){
            Intent i = new Intent(this, PerfilAlta.class);
            startActivity(i);
        }

    public void registro(){
            Intent i = new Intent(this, TipoUsuario.class);
            startActivity(i);
        }


}


