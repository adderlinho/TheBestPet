package asoftwaresolution.thebestpet.adaptadores;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import asoftwaresolution.thebestpet.Session.SessionManager;
import asoftwaresolution.thebestpet.pojo.Mascota;
import asoftwaresolution.thebestpet.R;
import asoftwaresolution.thebestpet.restApi.EndpointsApi;
import asoftwaresolution.thebestpet.restApi.adapter.RestApiAdapter;
import asoftwaresolution.thebestpet.restApi.model.FirebaseResponse;
import asoftwaresolution.thebestpet.restApi.model.LikeResponse;
import asoftwaresolution.thebestpet.restApi.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by AdderlyS on 26/09/2017.
 */

public class MascotasAdaptador extends RecyclerView.Adapter<MascotasAdaptador.MascotaViewHolder> {

    private ArrayList<Mascota> mascotas;
    private Activity activity;
    private SessionManager manager;

    public MascotasAdaptador(ArrayList<Mascota> mascotas, Activity activity)
    {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_mascota, parent, false);
        return new MascotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);
        manager = new SessionManager();
        //holder.imgvMascotaCV.setImageResource();
        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.ic_dog_face)
                .into(holder.imgvMascotaCV);
        holder.imgbLikeCV.setImageResource(R.drawable.bone_like);
        holder.tvMascotaNameCV.setText(mascota.getNombre());
        holder.tvMascotaLikesCV.setText(Integer.toString(mascota.getInstagram_likes()));
        holder.imgbLikesCV.setImageResource(R.drawable.bone_likes);

        holder.imgbLikeCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLikeMediaInstagram(mascota.getId_media(), mascota.getNombreCompleto());
                sendNotificationLike(mascota.getId_media(), manager.getPreferences(activity,"KEY_USERNAME"), mascota.getInstagram_id(), FirebaseInstanceId.getInstance().getToken());
                //ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                //constructorMascotas.darLikeMascota(mascota);
                mascota.setInstagram_likes(mascota.getInstagram_likes() + 1);
                holder.tvMascotaLikesCV.setText(Integer.toString(mascota.getInstagram_likes()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView   imgvMascotaCV;
        private ImageButton imgbLikeCV;
        private TextView    tvMascotaNameCV;
        private TextView    tvMascotaLikesCV;
        private ImageButton imgbLikesCV;

        public MascotaViewHolder(View view)
        {
            super(view);
            imgvMascotaCV =     (ImageView)       view.findViewById(R.id.imgvMascotaCV);
            imgbLikeCV =        (ImageButton)        view.findViewById(R.id.imgbLikeCV);
            tvMascotaNameCV =   (TextView)      view.findViewById(R.id.tvMascotaNameCV);
            tvMascotaLikesCV =  (TextView)     view.findViewById(R.id.tvMascotaLikesCV);
            imgbLikesCV =       (ImageButton)       view.findViewById(R.id.imgbLikesCV);
        }
    }

    public void setLikeMediaInstagram(String id_media, final String username)
    {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram();
        Call<UsuarioResponse> usuarioResponseCall = endpointsApi.setLikeMedia(id_media);
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse =  response.body();
                Toast.makeText(activity, "Diste like a " + username, Toast.LENGTH_SHORT).show();
                Log.i("Usuario: ", username);
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Toast.makeText(activity, "!Algo pasó en la conexión! Intenta de nuevo.", Toast.LENGTH_LONG).show();
                Log.i("FALLO LA CONEXIÓN", t.toString());
            }
        });
    }

    public void sendNotificationLike(String id_media, String username, String id_usuario_instagram, String id_dispositivo)
    {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiHeroku();
        Call<FirebaseResponse> firebaseResponseCall = endpointsApi.registrarLikeMedia(id_media, username, id_usuario_instagram, id_dispositivo);
        firebaseResponseCall.enqueue(new Callback<FirebaseResponse>() {
            @Override
            public void onResponse(Call<FirebaseResponse> call, Response<FirebaseResponse> response) {
                FirebaseResponse firebaseResponse = response.body();
                Log.i("NOTIFICACIÓN ENVIADA: ", firebaseResponse.getId_dispositivo());
            }

            @Override
            public void onFailure(Call<FirebaseResponse> call, Throwable t) {
                Log.i("FALLO LA NOTIFICACIÓN: ", t.toString());
            }
        });
    }
}
