package com.dannyang27.sportpoints.activities.Interfaz;

import com.dannyang27.sportpoints.activities.Fabricas.Empresa;
import com.dannyang27.sportpoints.activities.Fabricas.Jugador;
import com.dannyang27.sportpoints.activities.Modelos.Participante;
import com.dannyang27.sportpoints.activities.Modelos.Usuario;

/**
 * Created by Pablo_Fernandez on 14/5/17.
 */

public interface UsuarioFactoryMethod {

    public Usuario createEntidad(Integer tipoUser);

}
