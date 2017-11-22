package asoftwaresolution.thebestpet.restApi;

import asoftwaresolution.thebestpet.restApi.model.FirebaseResponse;
import asoftwaresolution.thebestpet.restApi.model.LikeResponse;
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

    //https://api.instagram.com/v1/users/search?q=jack&access_token=ACCESS-TOKEN
    @GET(ConstantesRestApi.URL_GET_USER_DATA)
    Call<UsuarioResponse> getDataUsuario(@Query("q") String username,
                                         @Query("access_token") String token);

    //https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN
    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USERS)
    Call<MascotasResponse> getRecentMediaPerfil(@Path("user-id") String id);

    //https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN
    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USERS)
    Call<MascotasResponse> getRecentMedia(@Path("user-id") String id);

    //https://api.instagram.com/v1/media/{media-id}/likes
    @POST(ConstantesRestApi.URL_SET_LIKE_MEDIA)
    Call<UsuarioResponse> setLikeMedia(@Path("media-id") String id_media);

    //https://powerful-anchorage-22855.herokuapp.com/registrar-usuario
    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN)
    Call<FirebaseResponse> registrarTokenID(@Field("id_dispositivo") String token, @Field("id_usuario_instagram") String id_user_instragram);

    //https://powerful-anchorage-22855.herokuapp.com/registrar-like
    @GET(ConstantesRestApi.KEY_GET_LIKE)
    Call<FirebaseResponse> registrarLikeMedia(@Path("id_foto_instagram") String id_foto_instagram, @Path("usuario_instagram") String usuario_instagram, @Path("id_usuario_instagram") String id_usuario_instagram, @Path("id_dispositivo") String id_dispositivo);
}
