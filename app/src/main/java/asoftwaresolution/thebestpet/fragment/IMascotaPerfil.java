package asoftwaresolution.thebestpet.fragment;

import java.util.ArrayList;

import asoftwaresolution.thebestpet.adaptadores.MascotasAdaptador;
import asoftwaresolution.thebestpet.adaptadores.PerfilAdaptador;
import asoftwaresolution.thebestpet.pojo.Mascota;
import asoftwaresolution.thebestpet.pojo.Usuario;

/**
 * Created by AdderlyS on 13/10/2017.
 */

public interface IMascotaPerfil
{
    public void generarGridLayout();

    public PerfilAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorML(PerfilAdaptador adaptador);

    public void inicializarControles(ArrayList<Usuario> usuarios);
}
