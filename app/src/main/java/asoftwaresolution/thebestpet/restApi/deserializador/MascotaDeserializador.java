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
import asoftwaresolution.thebestpet.restApi.JsonKeys;
import asoftwaresolution.thebestpet.restApi.model.MascotasResponse;

/**
 * Created by AdderlyS on 26/10/2017.
 */

public class MascotaDeserializador implements JsonDeserializer<MascotasResponse> {

    @Override
    public MascotasResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotasResponse mascotasResponse = gson.fromJson(json, MascotasResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        mascotasResponse.setMascotas(deserializarMascotaDeJson(mascotaResponseData));
        return mascotasResponse;
    }

    private ArrayList<Mascota> deserializarMascotaDeJson(JsonArray mascotaResponseData) {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        for (int i = 0; i < mascotaResponseData.size(); i++) {
            JsonObject mascotaResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();

            JsonObject userJson             = mascotaResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id                       = userJson.get(JsonKeys.USER_ID).getAsString();
            String full_name                = userJson.get(JsonKeys.USER_FULLNAME).getAsString();

            JsonObject imageJson            = mascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson    = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String     urlFoto              = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson            = mascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int        likes                = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            String id_media                 = mascotaResponseData.get(i).getAsJsonObject().get(JsonKeys.ID_MEDIA).getAsString();

            Mascota mascotaActual = new Mascota();
            mascotaActual.setInstagram_id(id);
            mascotaActual.setNombreCompleto(full_name);
            mascotaActual.setUrlFoto(urlFoto);
            mascotaActual.setInstagram_likes(likes);
            mascotaActual.setId_media(id_media);

            mascotas.add(mascotaActual);
        }
        return mascotas;
    }
}
