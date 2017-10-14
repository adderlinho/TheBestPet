package asoftwaresolution.thebestpet.presentador;

import android.content.Context;

import java.util.ArrayList;

import asoftwaresolution.thebestpet.IMascotasFavoritas;
import asoftwaresolution.thebestpet.db.ConstructorMascotas;
import asoftwaresolution.thebestpet.fragment.IMascotasListado;
import asoftwaresolution.thebestpet.pojo.Mascota;

/**
 * Created by AdderlyS on 13/10/2017.
 */

public class MascotasFavoritasPresenter implements IMascotasFavoritasPresenter {

    private IMascotasFavoritas iMascotasFavoritas;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotasFavoritas;

    public MascotasFavoritasPresenter(IMascotasFavoritas iMascotasFavoritas, Context context) {
        this.iMascotasFavoritas = iMascotasFavoritas;
        this.context = context;
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotasFavoritas = constructorMascotas.obtenerMascotasFavoritas();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iMascotasFavoritas.inicializarAdaptadorML(iMascotasFavoritas.crearAdaptador(mascotasFavoritas));
        iMascotasFavoritas.generarLinearLayoutVertical();
    }
}
