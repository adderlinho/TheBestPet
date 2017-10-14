package asoftwaresolution.thebestpet.presentador;

import android.content.Context;

import java.util.ArrayList;

import asoftwaresolution.thebestpet.adaptadores.MascotasAdaptador;
import asoftwaresolution.thebestpet.db.ConstructorMascotas;
import asoftwaresolution.thebestpet.fragment.IMascotasListado;
import asoftwaresolution.thebestpet.pojo.Mascota;

/**
 * Created by AdderlyS on 13/10/2017.
 */

public class MascotasListadoFragmentPresenter implements IMascotasListadoFragmentPresenter {

    private IMascotasListado iMascotasListado;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public MascotasListadoFragmentPresenter(IMascotasListado iMascotasListado, Context context) {
        this.iMascotasListado = iMascotasListado;
        this.context = context;
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iMascotasListado.inicializarAdaptadorML(iMascotasListado.crearAdaptador(mascotas));
        iMascotasListado.generarLinearLayoutVertical();
    }
}
