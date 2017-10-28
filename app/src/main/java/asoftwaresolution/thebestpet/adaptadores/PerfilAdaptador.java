package asoftwaresolution.thebestpet.adaptadores;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import asoftwaresolution.thebestpet.R;
import asoftwaresolution.thebestpet.pojo.Mascota;

/**
 * Created by AdderlyS on 26/09/2017.
 */

public class PerfilAdaptador extends RecyclerView.Adapter<PerfilAdaptador.MascotaViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;

    public PerfilAdaptador(ArrayList<Mascota> mascotas, Activity activity)
    {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new MascotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);
        //holder.imgvMascotaCV.setImageResource(mascota.getImagen());
                Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.balto)
                .into(holder.imgvMascotaCV);
        holder.tvMascotaLikesCV.setText(Integer.toString(mascota.getInstagram_likes()));
        holder.imgbLikesCV.setImageResource(R.drawable.bone_likes);
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView   imgvMascotaCV;
        private TextView    tvMascotaLikesCV;
        private ImageButton imgbLikesCV;

        public MascotaViewHolder(View view)
        {
            super(view);
            imgvMascotaCV =     (ImageView)       view.findViewById(R.id.imgvMascotaCV);
            tvMascotaLikesCV =  (TextView)     view.findViewById(R.id.tvMascotaLikesCV);
            imgbLikesCV =       (ImageButton)       view.findViewById(R.id.imgbLikesCV);
        }
    }
}
