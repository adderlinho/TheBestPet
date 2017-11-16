package asoftwaresolution.thebestpet.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import asoftwaresolution.thebestpet.adaptadores.MascotasAdaptador;
import asoftwaresolution.thebestpet.db.ConstructorMascotas;
import asoftwaresolution.thebestpet.fragment.IMascotasListado;
import asoftwaresolution.thebestpet.pojo.Mascota;
import asoftwaresolution.thebestpet.pojo.Usuario;
import asoftwaresolution.thebestpet.restApi.ConstantesRestApi;
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

public class MascotasListadoFragmentPresenter implements IMascotasListadoFragmentPresenter {

    private IMascotasListado iMascotasListado;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;
    private ArrayList<Mascota> mascotas_encontradas = new ArrayList<>();

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
        iMascotasListado.inicializarAdaptadorML(iMascotasListado.crearAdaptador(mascotas_encontradas));
        iMascotasListado.generarLinearLayoutVertical();
    }


    @Override
    public void obtenerDataUsuario(String usuario) {
        try
        {
            RestApiAdapter restApiAdapter = new RestApiAdapter();
            Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorUserData();
            EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
            Call<UsuarioResponse> usuarioResponseCall = endpointsApi.getDataUsuario(usuario, ConstantesRestApi.ACCESS_TOKEN);
            usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
                @Override
                public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                    UsuarioResponse usuarioResponse =  response.body();
                    //usuarios = usuarioResponse.getId();
                    for (int i = 0; i < usuarioResponse.getId().size(); i++)
                    {
                        obtenerMediosRecientes(usuarioResponse.getId().get(i).getId_instagram().toString());
                    }
                }

                @Override
                public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                    Toast.makeText(context, "!Algo pasó en la conexión! Intenta de nuevo.", Toast.LENGTH_LONG).show();
                    Log.i("FALLO LA CONEXIÓN", t.toString());
                }
            });
        }
        catch (Exception ex)
        {
            Toast.makeText(context, "Error: " + ex.toString(), Toast.LENGTH_SHORT).show();
            Log.i("ERROR: ", ex.toString());
        }
    }

    @Override
    public void obtenerMediosRecientes(String id_usuario) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotasResponse> mascotasResponseCall = endpointsApi.getRecentMedia(id_usuario);
        mascotasResponseCall.enqueue(new Callback<MascotasResponse>() {
            @Override
            public void onResponse(Call<MascotasResponse> call, Response<MascotasResponse> response) {
                MascotasResponse mascotasResponse =  response.body();
                mascotas = mascotasResponse.getMascotas();
                for(int i = 0; i < mascotas.size(); i++)
                {
                    Mascota mascotaActual = new Mascota();
                    mascotaActual.setInstagram_id(mascotas.get(i).getInstagram_id());
                    mascotaActual.setUrlFoto(mascotas.get(i).getUrlFoto());
                    mascotaActual.setNombreCompleto(mascotas.get(i).getNombreCompleto());
                    mascotaActual.setInstagram_likes(mascotas.get(i).getInstagram_likes());
                    mascotas_encontradas.add(mascotaActual);
                }
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotasResponse> call, Throwable t) {
                Toast.makeText(context, "!Algo pasó en la conexión! Intenta de nuevo.", Toast.LENGTH_LONG).show();
                Log.i("FALLO LA CONEXIÓN", t.toString());
            }
        });
    }
}
