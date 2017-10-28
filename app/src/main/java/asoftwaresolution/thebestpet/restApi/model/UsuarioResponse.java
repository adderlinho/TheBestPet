package asoftwaresolution.thebestpet.restApi.model;

import java.util.ArrayList;

import asoftwaresolution.thebestpet.pojo.Mascota;
import asoftwaresolution.thebestpet.pojo.Usuario;

/**
 * Created by AdderlyS on 25/10/2017.
 */

public class UsuarioResponse {
    ArrayList<Usuario> usuarios;

    public ArrayList<Usuario> getUsuario() {
        return usuarios;
    }

    public void setUsuario(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
