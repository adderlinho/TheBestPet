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
import asoftwaresolution.thebestpet.presentador.IMascotasListadoFragmentPresenter;
import asoftwaresolution.thebestpet.presentador.MascotasListadoFragmentPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MascotasListado extends Fragment implements IMascotasListado {
    private ArrayList<Mascota> mascotas;
    private RecyclerView rvMascotas;
    private IMascotasListadoFragmentPresenter presenter;

    public MascotasListado() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mascotas_listado, container, false);

        rvMascotas = (RecyclerView) view.findViewById(R.id.rvMascotas);
        presenter = new MascotasListadoFragmentPresenter(this, getContext());
        presenter.obtenerMascotasBaseDatos();
        return view;
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(linearLayoutManager);
    }

    @Override
    public MascotasAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotasAdaptador adapter = new MascotasAdaptador(mascotas, getActivity());
        return adapter;
    }

    @Override
    public void inicializarAdaptadorML(MascotasAdaptador adaptador) {
        rvMascotas.setAdapter(adaptador);
    }
}
