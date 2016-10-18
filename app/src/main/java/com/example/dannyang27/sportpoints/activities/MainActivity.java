package com.example.dannyang27.sportpoints.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.dannyang27.sportpoints.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //cargar fuentes
        TextView title_txt = (TextView) findViewById(R.id.title_txt);
        title_txt.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/AlfaSlabOne-Regular.ttf"));
    }

    public void onclick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.signIn_btn:
                intent = new Intent(this, PerfilAlta.class);
                break;
            case R.id.login_btn:
                intent = new Intent(this, TipoUsuario.class);
                break;
        }
        startActivity(intent);
    }
}


