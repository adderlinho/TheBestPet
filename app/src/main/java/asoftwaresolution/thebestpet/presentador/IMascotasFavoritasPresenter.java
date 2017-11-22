package asoftwaresolution.thebestpet.presentador;

import java.util.ArrayList;

import asoftwaresolution.thebestpet.pojo.Mascota;

/**
 * Created by AdderlyS on 13/10/2017.
 */

public interface IMascotasFavoritasPresenter
{
    public void obtenerMascotasBaseDatos();

    public void mostrarMascotasRV(ArrayList<Mascota> mascotas);

    public void obtenerMediosRecientesPerfil(String id_usuario);
}
