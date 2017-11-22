package asoftwaresolution.thebestpet.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import asoftwaresolution.thebestpet.IMascotasFavoritas;
import asoftwaresolution.thebestpet.db.ConstructorMascotas;
import asoftwaresolution.thebestpet.fragment.IMascotasListado;
import asoftwaresolution.thebestpet.pojo.Mascota;
import asoftwaresolution.thebestpet.restApi.EndpointsApi;
import asoftwaresolution.thebestpet.restApi.adapter.RestApiAdapter;
import asoftwaresolution.thebestpet.restApi.model.MascotasResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by AdderlyS on 13/10/2017.
 */

public class MascotasFavoritasPresenter implements IMascotasFavoritasPresenter {

    private IMascotasFavoritas iMascotasFavoritas;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotasFavoritas = new ArrayList<>();;

    public MascotasFavoritasPresenter(IMascotasFavoritas iMascotasFavoritas, Context context) {
        this.iMascotasFavoritas = iMascotasFavoritas;
        this.context = context;
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotasFavoritas = constructorMascotas.obtenerMascotasFavoritas();
        //mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV(ArrayList<Mascota> mascotasFavoritas) {
        iMascotasFavoritas.inicializarAdaptadorML(iMascotasFavoritas.crearAdaptador(mascotasFavoritas));
        iMascotasFavoritas.generarLinearLayoutVertical();
    }

    @Override
    public void obtenerMediosRecientesPerfil(String id_usuario) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotasResponse> mascotasResponseCall = endpointsApi.getRecentMediaPerfil(id_usuario);
        mascotasResponseCall.enqueue(new Callback<MascotasResponse>() {
            @Override
            public void onResponse(Call<MascotasResponse> call, Response<MascotasResponse> response) {
                MascotasResponse mascotasResponse =  response.body();
                mascotasFavoritas = mascotasResponse.getMascotas();

                Collections.sort(mascotasFavoritas, new Comparator<Mascota>() {
                    @Override
                    public int compare(Mascota mascota1, Mascota mascota2) {
                        return new Integer(mascota2.getInstagram_likes()).compareTo(new Integer(mascota1.getInstagram_likes()));
                    }
                });

                ArrayList<Mascota> favoritas = new ArrayList<Mascota>();
                for(int i = 0; i <= 4; i++)
                {
                    favoritas.add(new Mascota(mascotasFavoritas.get(i).getInstagram_id(), mascotasFavoritas.get(i).getUrlFoto(), mascotasFavoritas.get(i).getNombreCompleto(), mascotasFavoritas.get(i).getInstagram_likes(), mascotasFavoritas.get(i).getId_media()));
                }

                mostrarMascotasRV(favoritas);
            }

            @Override
            public void onFailure(Call<MascotasResponse> call, Throwable t) {
                Toast.makeText(context, "!Algo pasó en la conexión! Intenta de nuevo.", Toast.LENGTH_LONG).show();
                Log.i("FALLO LA CONEXIÓN", t.toString());
            }
        });
    }
}
