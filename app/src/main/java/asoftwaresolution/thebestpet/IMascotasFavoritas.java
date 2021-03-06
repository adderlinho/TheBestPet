package asoftwaresolution.thebestpet;

import java.util.ArrayList;

import asoftwaresolution.thebestpet.adaptadores.MascotasAdaptador;
import asoftwaresolution.thebestpet.adaptadores.MascotasFavoritasAdaptador;
import asoftwaresolution.thebestpet.pojo.Mascota;

/**
 * Created by AdderlyS on 13/10/2017.
 */

public interface IMascotasFavoritas
{
    public void generarLinearLayoutVertical();

    public MascotasFavoritasAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorML(MascotasFavoritasAdaptador adaptador);
}
