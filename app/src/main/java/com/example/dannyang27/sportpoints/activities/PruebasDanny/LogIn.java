package com.example.dannyang27.sportpoints.activities.PruebasDanny;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;

public class LogIn extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginBtn;
    private Button registrateBtn;
    private Button gmailBtn;
    private TextView titleTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //Cargar fuentes
        TextView titleTxt = (TextView) findViewById(R.id.textView_title);
        titleTxt.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/PassionOne-Bold.ttf"));

        loginBtn = (Button) findViewById(R.id.btn_login);
        gmailBtn = (Button) findViewById(R.id.btn_gmail);



    }

    public void onclick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.txt_registrarse:
                intent = new Intent(this, Registro.class);
                // intent.putExtra("id_usuario",id_usuario);
                break;
            case R.id.btn_login:
                intent = new Intent(this, PruebaTab.class);
                break;
        }
        startActivity(intent);
    }


}
