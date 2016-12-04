package com.example.dannyang27.sportpoints.activities.Principal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dannyang27.sportpoints.R;
import com.example.dannyang27.sportpoints.activities.Principal.PPrincipal;
import com.example.dannyang27.sportpoints.activities.Principal.Registro;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LogIn extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "SportPoints";
    private static final int RC_SIGN_IN_GMAIL = 9001;
    private static final int RC_REGISTER = 9002;
    private static final int RC_LOGIN = 9003;
    private static final int RC_REGISTER_FAIL = 9004;

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginBtn;
    private TextView registrateBtn;
    private Button gmailBtn;
    private View rootView;

    private GoogleApiClient mGoogleApiClient;
    private String emailUsuario;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this , this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

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

        rootView = (View) findViewById(R.id.activity_log_in);
        emailEditText = (EditText) findViewById(R.id.editText_email);
        passwordEditText = (EditText) findViewById(R.id.editText_password);
        loginBtn = (Button) findViewById(R.id.btn_login);
        registrateBtn = (TextView) findViewById(R.id.txt_registrarse);
        gmailBtn = (Button) findViewById(R.id.btn_gmail);

        gmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInGoogle();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singInEmail();
            }
        });

        registrateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegistro();
            }
        });
    }

    public void showPruebaTab(){
        Intent i = new Intent(this, PPrincipal.class);
        i.putExtra("Email",emailUsuario);
        startActivityForResult(i, RC_LOGIN);
    }
    public void showRegistro(){
        Intent i = new Intent(this, Registro.class);
        startActivityForResult(i, RC_REGISTER);
    }

    // --- Comun Logeo ---

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        mAuth.signOut();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN_GMAIL) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                //if(isOnlineNet()) {
                    GoogleSignInAccount acct = result.getSignInAccount();
                    firebaseAuthWithGoogle(acct);
                    emailUsuario = acct.getEmail();
                    showPruebaTab();
               // } else
                //    Snackbar.make(rootView, "Problemas de conexión, inténtelo más tarde...", Snackbar.LENGTH_LONG).show();
            } else {
                // El usuario ha rechazado logearse con la cuenta
                Log.d(TAG, requestCode+" Fallo");
            }
        }else if(requestCode == RC_LOGIN){
            mAuth.signOut();
        }else if(requestCode == RC_REGISTER){
            if(resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Cuenta creada con exito.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // --- Logeo con Google ---

    private void signInGoogle() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // User is signed in
            Toast.makeText(getApplicationContext(), "Logeado con la cuenta de Google.", Toast.LENGTH_SHORT).show();
            showPruebaTab();
        } else {
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            startActivityForResult(signInIntent, RC_SIGN_IN_GMAIL);
        }

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(getApplicationContext(), "La autentificación ha fallado.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // -- Fin Logeo Google ---

    private void singInEmail(){
        String email = emailEditText.getText().toString();
        final String password = passwordEditText.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Introduce tu email!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Introduce tu contraseña!", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            if (password.length() < 6) {
                                passwordEditText.setError("Debe tener 6 carácteres mínimo.");
                            } else if(task.getException().getMessage().equals("The password is invalid or the user does not have a password.")) {
                                Toast.makeText(getApplicationContext(), "La contraseña no es válida.", Toast.LENGTH_LONG).show();
                            } else if(task.getException().getMessage().equals("There is no user record corresponding to this identifier. The user may have been deleted.")) {
                                Toast.makeText(getApplicationContext(), "El email no es válido.", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Se ha perdido la conexión.", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            emailUsuario = mAuth.getCurrentUser().getEmail();
                            Toast.makeText(getApplicationContext(), "Logeado tu cuenta de SportPoints.", Toast.LENGTH_SHORT).show();
                            showPruebaTab();
                        }
                    }
                });
    }

    //Comprobar si tenemos internet en un momento determinado
    public Boolean isOnlineNet() {
        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");
            int val = p.waitFor();
            boolean reachable = (val == 0);
            if (!reachable) {
                p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.upv.es");
                val = p.waitFor();
                reachable = (val == 0);
            }
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

}