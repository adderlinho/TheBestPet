package asoftwaresolution.thebestpet.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import asoftwaresolution.thebestpet.R;
import asoftwaresolution.thebestpet.adaptadores.MascotasAdaptador;
import asoftwaresolution.thebestpet.adaptadores.PerfilAdaptador;
import asoftwaresolution.thebestpet.pojo.Mascota;

/**
 * A simple {@link Fragment} subclass.
 */
public class MascotaPerfil extends Fragment {

    ArrayList<Mascota> mascotas;
    RecyclerView rvMascotaPerfil;

    public MascotaPerfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mascota_perfil, container, false);

        rvMascotaPerfil = (RecyclerView) view.findViewById(R.id.rvMascotaPerfil);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotaPerfil.setLayoutManager(gridLayoutManager);
        inicializarListaMascotas();
        inicializarAdaptador(mascotas);

        return view;
    }

    public void inicializarAdaptador(ArrayList<Mascota> mascotas)
    {
        PerfilAdaptador adapter = new PerfilAdaptador(mascotas, getActivity());
        rvMascotaPerfil.setAdapter(adapter);
    }

    public void inicializarListaMascotas()
    {
        mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota(R.drawable.balto, "Balto", 4));
        mascotas.add(new Mascota(R.drawable.balto2, "Balto", 9));
        mascotas.add(new Mascota(R.drawable.balto3, "Balto", 3));
        mascotas.add(new Mascota(R.drawable.balto4, "Balto", 3));
        mascotas.add(new Mascota(R.drawable.balto5, "Balto", 10));
        mascotas.add(new Mascota(R.drawable.balto6, "Balto", 11));
    }

}
