package asoftwaresolution.thebestpet.adaptadores;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import asoftwaresolution.thebestpet.R;
import asoftwaresolution.thebestpet.db.ConstructorMascotas;
import asoftwaresolution.thebestpet.pojo.Mascota;

/**
 * Created by AdderlyS on 26/09/2017.
 */

public class MascotasFavoritasAdaptador extends RecyclerView.Adapter<MascotasFavoritasAdaptador.MascotaViewHolder> {

    private ArrayList<Mascota> mascotas;
    private Activity activity;

    public MascotasFavoritasAdaptador(ArrayList<Mascota> mascotas, Activity activity)
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
        //holder.imgvMascotaCV.setImageResource(mascota.getImagen());
        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.ic_dog_face)
                .into(holder.imgvMascotaCV);
        holder.imgbLikeCV.setImageResource(R.drawable.bone_like);
        //holder.tvMascotaNameCV.setText(mascota.getNombreCompleto());
        holder.tvMascotaLikesCV.setText(Integer.toString(mascota.getInstagram_likes()));
        holder.imgbLikesCV.setImageResource(R.drawable.bone_likes);

        holder.imgbLikeCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Diste like a " + mascota.getNombre(), Toast.LENGTH_SHORT).show();
                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                constructorMascotas.darLikeMascota(mascota);
                //mascota.setLikes(mascota.getLikes() + 1);
                holder.tvMascotaLikesCV.setText(Integer.toString(constructorMascotas.obtenerLikesMascota(mascota)));
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
}
