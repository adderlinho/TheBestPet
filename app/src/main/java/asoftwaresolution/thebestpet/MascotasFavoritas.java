package asoftwaresolution.thebestpet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import asoftwaresolution.thebestpet.adaptadores.MascotasAdaptador;
import asoftwaresolution.thebestpet.pojo.Mascota;
import asoftwaresolution.thebestpet.presentador.IMascotasFavoritasPresenter;
import asoftwaresolution.thebestpet.presentador.IMascotasListadoFragmentPresenter;
import asoftwaresolution.thebestpet.presentador.MascotasFavoritasPresenter;
import asoftwaresolution.thebestpet.presentador.MascotasListadoFragmentPresenter;

public class MascotasFavoritas extends AppCompatActivity implements IMascotasFavoritas {

    private ArrayList<Mascota> mascotas;
    private RecyclerView rvMascotasFavoritas;
    private IMascotasFavoritasPresenter presenter;

    public MascotasFavoritas() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        rvMascotasFavoritas = (RecyclerView) findViewById(R.id.rvMascotasFavoritas);
        presenter = new MascotasFavoritasPresenter(this, getBaseContext());
        presenter.obtenerMascotasBaseDatos();

        miActionBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MascotasFavoritas.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotasFavoritas.setLayoutManager(linearLayoutManager);
    }

    @Override
    public MascotasAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotasAdaptador adapter = new MascotasAdaptador(mascotas, MascotasFavoritas.this);
        return adapter;
    }

    @Override
    public void inicializarAdaptadorML(MascotasAdaptador adaptador) {
        rvMascotasFavoritas.setAdapter(adaptador);
    }
}
