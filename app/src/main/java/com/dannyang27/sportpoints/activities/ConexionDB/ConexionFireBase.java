package com.dannyang27.sportpoints.activities.ConexionDB;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Pablo_Fernandez on 10/6/17.
 */

public class ConexionFireBase {

    private FirebaseAuth mAuth;
    private boolean activa = false;

    public ConexionFireBase() {

        if(!activa){
            mAuth = FirebaseAuth.getInstance();
            activa = true;
        }else{
            System.out.println("Solo una conexion por usuario");
        }

    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }
}
