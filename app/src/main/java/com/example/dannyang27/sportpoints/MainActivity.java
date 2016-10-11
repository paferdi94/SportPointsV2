package com.example.dannyang27.sportpoints;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    //comentario de prueba
    //2 comentarios

    private EditText keyField;
    private EditText valueField;
    private Button buttonApp;

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        keyField = (EditText) findViewById(R.id.key);
        valueField = (EditText) findViewById(R.id.value);
        buttonApp = (Button) findViewById(R.id.buttonApp);

        buttonApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String key = keyField.getText().toString();
                String value = valueField.getText().toString();
                root.child(key).setValue(value);

            }
        });

    }

}
