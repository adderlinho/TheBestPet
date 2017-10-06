package asoftwaresolution.thebestpet.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import asoftwaresolution.thebestpet.R;
import asoftwaresolution.thebestpet.adaptadores.MascotasAdaptador;
import asoftwaresolution.thebestpet.pojo.Mascota;

/**
 * A simple {@link Fragment} subclass.
 */
public class MascotasListado extends Fragment {
    ArrayList<Mascota> mascotas;
    RecyclerView rvMascotas;

    public MascotasListado() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mascotas_listado, container, false);

        rvMascotas = (RecyclerView) view.findViewById(R.id.rvMascotas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(linearLayoutManager);
        inicializarListaMascotas();
        inicializarAdaptador(mascotas);

        return view;
    }

    public void inicializarAdaptador(ArrayList<Mascota> mascotas)
    {
        MascotasAdaptador adapter = new MascotasAdaptador(mascotas, getActivity());
        rvMascotas.setAdapter(adapter);
    }

    public void inicializarListaMascotas()
    {
        mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota(R.drawable.dog1, "Elvis", 3));
        mascotas.add(new Mascota(R.drawable.dog2, "Zoey", 2));
        mascotas.add(new Mascota(R.drawable.dog3, "Hunter", 6));
        mascotas.add(new Mascota(R.drawable.dog4, "Izzy", 3));
        mascotas.add(new Mascota(R.drawable.dog5, "Kobe", 4));
        mascotas.add(new Mascota(R.drawable.dog6, "Sasha", 2));
        mascotas.add(new Mascota(R.drawable.dog7, "Trixie", 6));
        mascotas.add(new Mascota(R.drawable.dog8, "Rufus", 2));
        mascotas.add(new Mascota(R.drawable.dog9, "Simba", 5));
        mascotas.add(new Mascota(R.drawable.dog10, "Cookie", 7));
    }
}
