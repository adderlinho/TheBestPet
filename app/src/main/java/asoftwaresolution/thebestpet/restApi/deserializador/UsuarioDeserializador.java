package asoftwaresolution.thebestpet.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import asoftwaresolution.thebestpet.pojo.Mascota;
import asoftwaresolution.thebestpet.pojo.Usuario;
import asoftwaresolution.thebestpet.restApi.JsonKeys;
import asoftwaresolution.thebestpet.restApi.model.MascotasResponse;
import asoftwaresolution.thebestpet.restApi.model.UsuarioResponse;

/**
 * Created by AdderlyS on 26/10/2017.
 */

public class UsuarioDeserializador implements JsonDeserializer<UsuarioResponse> {

    @Override
    public UsuarioResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        UsuarioResponse usuarioResponse = gson.fromJson(json, UsuarioResponse.class);
        JsonArray usuarioResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        usuarioResponse.setUsuario(deserializarUsuarioDeJson(usuarioResponseData));
        return usuarioResponse;
    }

    private ArrayList<Usuario> deserializarUsuarioDeJson(JsonArray usuarioResponseData) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        for (int i = 0; i < usuarioResponseData.size(); i++) {
            JsonObject usuarioResponseDataObject = usuarioResponseData.get(i).getAsJsonObject();

            String id                      = usuarioResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            String username                = usuarioResponseDataObject.get(JsonKeys.USERNAME).getAsString();
            String profile_picture         = usuarioResponseDataObject.get(JsonKeys.PROFILE_PICTURE).getAsString();

            Usuario usuarioActual = new Usuario();
            usuarioActual.setId(id);
            usuarioActual.setUsername(username);
            usuarioActual.setProfile_picture(profile_picture);

            usuarios.add(usuarioActual);
        }
        return usuarios;
    }
}
