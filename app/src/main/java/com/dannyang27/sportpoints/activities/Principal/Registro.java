package com.dannyang27.sportpoints.activities.Principal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.dannyang27.sportpoints.R;
import com.dannyang27.sportpoints.activities.Fabricas.Empresa;
import com.dannyang27.sportpoints.activities.Fabricas.UsuarioFactory;
import com.dannyang27.sportpoints.activities.Interfaz.UsuarioFactoryMethod;
import com.dannyang27.sportpoints.activities.Modelos.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.dannyang27.sportpoints.activities.Fabricas.Jugador;

import static java.lang.Integer.parseInt;

public class Registro extends AppCompatActivity {
    private ImageButton profile;
    private EditText nombreEditText;
    private EditText emailEditText;
    private EditText pass1EditText;
    private EditText pass2EditText;
    private EditText fechaNacimientoEditText;
    private EditText telefonoEditText;
    private EditText CIFEditText;
    private Button cancelar_btn;
    private Button aceptar_btn;
    private RadioButton jug_radioB;
    private RadioButton emp_radioB;
    private DatabaseReference mDatabase;


    private static final String TAG = "SportPoints";
    private static final int RC_REGISTER = 9002;
    private static final int RC_LOGIN = 9003;
    private static final int empresa = 2;
    private static final int jugador  = 1;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String login;
    private String password;
    private String nombre;
    private String apellidos;
    private String email;
    private String Cif;
    private int telefono;
    private Date fechaNacimiento;
    private Integer tipoUser;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_md);
        mAuth = FirebaseAuth.getInstance();

        //Obtener referencia a Firebase/Eventos
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        //profile = (ImageButton) findViewById(R.id.imageBtn_md_registro);
        jug_radioB = (RadioButton) findViewById(R.id.registro_radioButton_Jug);
        emp_radioB = (RadioButton) findViewById(R.id.registro_radioButton_Emp);
        nombreEditText = (EditText) findViewById(R.id.nombre_md_registro);
        emailEditText = (EditText) findViewById(R.id.email_md_registro);
        pass1EditText = (EditText) findViewById(R.id.password_md_registro);
        pass2EditText = (EditText) findViewById(R.id.password2_md_registro);
        CIFEditText = (EditText) findViewById(R.id.cif_md_registro);
        fechaNacimientoEditText = (EditText) findViewById(R.id.fecha_md_registro);
        telefonoEditText = (EditText) findViewById(R.id.telefono_md_registro);
        cancelar_btn = (Button) findViewById(R.id.cancelar_md_registro);
        aceptar_btn = (Button) findViewById(R.id.aceptar_md_regsitro);


        cancelar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        aceptar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearCuenta();
            }
        });
    }

    public void crearCuenta(){
        String email = emailEditText.getText().toString();
        String pass1 = pass1EditText.getText().toString();
        String pass2 = pass2EditText.getText().toString();
        int tel_length = telefonoEditText.getText().length();
        if(
                nombreEditText.getText().toString().equals("") ||
                        emailEditText.getText().toString().equals("") ||
                        pass1EditText.getText().toString().equals("") ||
                        pass2EditText.getText().toString().equals("") ||
                        telefonoEditText.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Rellena todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!nombreEditText.getText().toString().matches("[a-zA-Z]+")){
            Toast.makeText(getApplicationContext(),"Solo se permiten letras en el nombre.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!pass1.equals(pass2)){
            Toast.makeText(getApplicationContext(),"Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(tel_length != 9){
            Log.d(TAG, tel_length+"");
            Toast.makeText(getApplicationContext(),"El telefono tiene que contener 9 números.", Toast.LENGTH_SHORT).show();
            return;
        }


        int telefono = parseInt(telefonoEditText.getText().toString());
        this.email = email;
        this.password = pass1;
        this.nombre = nombreEditText.getText().toString();
        this.apellidos = "";
        this.telefono = telefono;

        if(this.emp_radioB.isChecked()){
            this.tipoUser = empresa;

            if(CIFEditText.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Es obligatorio rellenar el campo CIF para las Empresas", Toast.LENGTH_SHORT).show();
                return;
            }else {
                this.Cif = CIFEditText.getText().toString();
            }

        }else{
            tipoUser = jugador;
            if(fechaNacimientoEditText.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Es obligatorio rellenar el campo Fecha de Nacimiento para los jugadores", Toast.LENGTH_SHORT).show();
                return;
            }
            try{
                String fecha = fechaNacimientoEditText.getText().toString();
                DateFormat format = new SimpleDateFormat("d-L-y", Locale.ENGLISH);
                this.fechaNacimiento = format.parse(fecha);
            }catch (ParseException e){
                Toast.makeText(getApplicationContext(), "La fecha de cumpleaños no es correcta (DD-MM-YYYY).", Toast.LENGTH_SHORT).show();
                return;
            }

        }


        // Cuenta básica.
            mAuth.createUserWithEmailAndPassword(email, pass1)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthWeakPasswordException e) {
                                    Toast.makeText(getApplicationContext(), "La contraseña tiene que tener mas carácteres.", Toast.LENGTH_SHORT).show();
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    Toast.makeText(getApplicationContext(), "El email no es válido.", Toast.LENGTH_SHORT).show();
                                } catch (FirebaseAuthUserCollisionException e) {
                                    Toast.makeText(getApplicationContext(), "Ya existe una cuenta con ese email.", Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "Se ha perdido la conexión.", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                guardarDatos();
                                setResult(RESULT_OK);
                                finish();
                            }
                        }
                    });
    }

    public void guardarDatos() {
        int index = email.indexOf('@');
        this.login = email.substring(0,index);
        String key = emailEditText.getText().toString().replace(".", "%2E");
        UsuarioFactoryMethod usufactory = new UsuarioFactory();
        DatabaseReference db;

        //fechaNacimiento.toString()

        if(tipoUser == 1) {

            db = mDatabase.child("Usuarios").child(key);

            Jugador jugador = (Jugador) usufactory.createEntidad(tipoUser);

            jugador.setNombre(nombre);
            jugador.setApellidos(apellidos);
            jugador.setEmail(email);
            jugador.setLogin(login);
            jugador.setPassword(password);
            jugador.setTelefono(telefono);
            jugador.setFechaNacimiento(fechaNacimiento.toString());
            jugador.setNumValoraciones(0);
            jugador.setValoracion(0);
            jugador.setTipoUser(tipoUser);


            db.setValue(jugador);

        }else{

            db = mDatabase.child("Empresas").child(key);

            Empresa empresa = (Empresa) usufactory.createEntidad(tipoUser);
            empresa.setNombre(nombre);
            empresa.setEmail(email);
            empresa.setCIF(Cif);
            empresa.setPassword(password);
            empresa.setTelefono(telefono);
            empresa.setTipoUser(tipoUser);

            db.setValue(empresa);
        }



        showPruebaTab();

       /* Map<String, Object> postValues = jugador.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Usuarios/" + key, postValues);
        mDatabase.updateChildren(childUpdates);*/
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void showPruebaTab(){
        Intent i = new Intent(this, PPrincipal.class);
        startActivityForResult(i, RC_LOGIN);
    }
}
