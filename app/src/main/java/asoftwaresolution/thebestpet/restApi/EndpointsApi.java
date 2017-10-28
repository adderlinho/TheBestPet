package asoftwaresolution.thebestpet.restApi;

import asoftwaresolution.thebestpet.fragment.MascotaPerfil;
import asoftwaresolution.thebestpet.restApi.model.MascotasResponse;
import asoftwaresolution.thebestpet.restApi.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by AdderlyS on 25/10/2017.
 */

public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotasResponse> getRecentMedia();

    @GET(ConstantesRestApi.URL_GET_USER_DATA)
    Call<UsuarioResponse> getDataUsuario();
}
