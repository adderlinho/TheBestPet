package asoftwaresolution.thebestpet.restApi;

import java.util.HashMap;
import java.util.Map;

import asoftwaresolution.thebestpet.fragment.MascotaPerfil;
import asoftwaresolution.thebestpet.restApi.model.FirebaseResponse;
import asoftwaresolution.thebestpet.restApi.model.MascotasResponse;
import asoftwaresolution.thebestpet.restApi.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by AdderlyS on 25/10/2017.
 */

public interface EndpointsApi {

    //https://api.instagram.com/v1/users/self/media/recent/?access_token=ACCESS-TOKEN
    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotasResponse> getRecentMedia(@Field("user-id") String id);

    //https://api.instagram.com/v1/users/search?q=jack&access_token=ACCESS-TOKEN
    @GET(ConstantesRestApi.URL_GET_USER_DATA)
    Call<UsuarioResponse> getDataUsuario(@Query("") String username,
                                         @Query("access_token")    String token);

    //https://powerful-anchorage-22855.herokuapp.com/registrar-usuario
    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN)
    Call<FirebaseResponse> registrarTokenID(@Field("token") String token, @Field("usuario_instagram") String user_instragram);
}
