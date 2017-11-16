package asoftwaresolution.thebestpet.presentador;

/**
 * Created by AdderlyS on 13/10/2017.
 */

public interface IMascotaPerfilPresenter
{
    public void obtenerMascotasBaseDatos();

    void insertarUsuario(String id_instagram, String id_firebase, String username);

    public void obtenerDataUsuario();

    public void obtenerMediosRecientes();

    public void mostrarDataUsuario();

    public void mostrarMascotasRV();
}
