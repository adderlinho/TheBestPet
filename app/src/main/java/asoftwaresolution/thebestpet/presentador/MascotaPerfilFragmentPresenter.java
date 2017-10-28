package asoftwaresolution.thebestpet.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import asoftwaresolution.thebestpet.db.ConstructorMascotas;
import asoftwaresolution.thebestpet.fragment.IMascotaPerfil;
import asoftwaresolution.thebestpet.fragment.IMascotasListado;
import asoftwaresolution.thebestpet.pojo.Mascota;
import asoftwaresolution.thebestpet.pojo.Usuario;
import asoftwaresolution.thebestpet.restApi.EndpointsApi;
import asoftwaresolution.thebestpet.restApi.adapter.RestApiAdapter;
import asoftwaresolution.thebestpet.restApi.model.MascotasResponse;
import asoftwaresolution.thebestpet.restApi.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by AdderlyS on 13/10/2017.
 */

public class MascotaPerfilFragmentPresenter implements IMascotaPerfilPresenter {

    private IMascotaPerfil iMascotaPerfil;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;
    private ArrayList<Usuario> usuarios;

    public MascotaPerfilFragmentPresenter(IMascotaPerfil iMascotaPerfil, Context context) {
        this.iMascotaPerfil = iMascotaPerfil;
        this.context = context;
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();
    }

    @Override
    public void obtenerDataUsuario() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorUserData();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<UsuarioResponse> usuarioResponseCall = endpointsApi.getDataUsuario();
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse =  response.body();
                usuarios = usuarioResponse.getUsuario();
                mostrarDataUsuario();
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Toast.makeText(context, "!Algo pasó en la conexión! Intenta de nuevo.", Toast.LENGTH_LONG).show();
                Log.i("FALLO LA CONEXIÓN", t.toString());
            }
        });
    }

        @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotasResponse> mascotasResponseCall = endpointsApi.getRecentMedia();
        mascotasResponseCall.enqueue(new Callback<MascotasResponse>() {
            @Override
            public void onResponse(Call<MascotasResponse> call, Response<MascotasResponse> response) {
                MascotasResponse mascotasResponse =  response.body();
                mascotas = mascotasResponse.getMascotas();
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotasResponse> call, Throwable t) {
                Toast.makeText(context, "!Algo pasó en la conexión! Intenta de nuevo.", Toast.LENGTH_LONG).show();
                Log.i("FALLO LA CONEXIÓN", t.toString());
            }
        });
    }

    @Override
    public void mostrarDataUsuario() {
        iMascotaPerfil.inicializarControles(usuarios);
    }

    @Override
    public void mostrarMascotasRV() {
        iMascotaPerfil.inicializarAdaptadorML(iMascotaPerfil.crearAdaptador(mascotas));
        iMascotaPerfil.generarGridLayout();
    }
}
