package asoftwaresolution.thebestpet.presentador;

import java.util.ArrayList;

import asoftwaresolution.thebestpet.pojo.Usuario;

/**
 * Created by AdderlyS on 13/10/2017.
 */

public interface IMascotasListadoFragmentPresenter
{
    public void obtenerDataUsuario(String usuario);

    public void obtenerMediosRecientes(String id_usuario);

    public void obtenerMascotasBaseDatos();

    public void mostrarMascotasRV();

}
