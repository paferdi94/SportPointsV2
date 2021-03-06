package com.dannyang27.sportpoints.activities.Fabricas;

import com.dannyang27.sportpoints.activities.Interfaz.UsuarioFactoryMethod;
import com.dannyang27.sportpoints.activities.Entidades.Empresa;
import com.dannyang27.sportpoints.activities.Entidades.Jugador;
import com.dannyang27.sportpoints.activities.Entidades.Participante;
import com.dannyang27.sportpoints.activities.Entidades.Usuario;

/**
 * Created by Pablo_Fernandez on 14/5/17.
 */

public class UsuarioFactory implements UsuarioFactoryMethod {


    public Usuario createEntidad(Integer tipoUser){

        if(tipoUser == 1){
            return new Jugador();
        }

        if(tipoUser == 2){
            return new Empresa();
        }

        if(tipoUser == 3){
            return new Participante();
        }

        return null;

    }

}
