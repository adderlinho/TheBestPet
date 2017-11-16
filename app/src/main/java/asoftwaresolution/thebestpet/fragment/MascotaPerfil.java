package asoftwaresolution.thebestpet.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import asoftwaresolution.thebestpet.ActivityConfiguracion;
import asoftwaresolution.thebestpet.MainActivity;
import asoftwaresolution.thebestpet.R;
import asoftwaresolution.thebestpet.adaptadores.MascotasAdaptador;
import asoftwaresolution.thebestpet.adaptadores.PerfilAdaptador;
import asoftwaresolution.thebestpet.pojo.Mascota;
import asoftwaresolution.thebestpet.pojo.Usuario;
import asoftwaresolution.thebestpet.presentador.IMascotaPerfilPresenter;
import asoftwaresolution.thebestpet.presentador.IMascotasListadoFragmentPresenter;
import asoftwaresolution.thebestpet.presentador.MascotaPerfilFragmentPresenter;
import asoftwaresolution.thebestpet.restApi.ConstantesRestApi;

/**
 * A simple {@link Fragment} subclass.
 */
public class MascotaPerfil extends Fragment implements IMascotaPerfil {

    private ArrayList<Mascota> mascotas;
    private RecyclerView rvMascotaPerfil;
    private IMascotaPerfilPresenter presenter;
    private CircularImageView cimgv_perfil;
    private TextView tv_MascotaNamePerfil;

    public MascotaPerfil() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mascota_perfil, container, false);

        cimgv_perfil            = (CircularImageView) view.findViewById(R.id.cimgv_perfil);
        tv_MascotaNamePerfil    = (TextView) view.findViewById(R.id.tv_MascotaNamePerfil);

        if(ConstantesRestApi.KEY_USERNAME != "")
        {
            rvMascotaPerfil = (RecyclerView) view.findViewById(R.id.rvMascotaPerfil);
            presenter = new MascotaPerfilFragmentPresenter(this, getContext());
            presenter.obtenerDataUsuario();
            presenter.obtenerMediosRecientes();
        }
        else
        {
            Intent intent = new Intent(getActivity(), ActivityConfiguracion.class);
            startActivity(intent);
        }
        return view;
    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvMascotaPerfil.setLayoutManager(gridLayoutManager);
    }

    @Override
    public PerfilAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        PerfilAdaptador adapter = new PerfilAdaptador(mascotas, getActivity());
        return adapter;
    }

    @Override
    public void inicializarAdaptadorML(PerfilAdaptador adaptador) {
        rvMascotaPerfil.setAdapter(adaptador);
    }

    @Override
    public void inicializarControles(ArrayList<Usuario> usuarios) {
        Picasso.with(getContext())
                .load(usuarios.get(0).getProfile_picture())
                .placeholder(R.drawable.balto)
                .into(cimgv_perfil);
        tv_MascotaNamePerfil.setText(usuarios.get(0).getUsername());
    }
}
