package asoftwaresolution.thebestpet;

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

public class MascotasFavoritas extends AppCompatActivity {

    ArrayList<Mascota> mascotas;
    RecyclerView rvMascotasFavoritas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        rvMascotasFavoritas = (RecyclerView) findViewById(R.id.rvMascotasFavoritas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotasFavoritas.setLayoutManager(linearLayoutManager);
        inicializarListaMascotas();
        inicializarAdaptador(mascotas);

        miActionBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MascotasFavoritas.this, MainActivity.class);
                intent.putExtra(getResources().getString(R.string.pmascotas), mascotas);
                startActivity(intent);
                finish();
            }
        });
    }

    public void inicializarAdaptador(ArrayList<Mascota> mascotas)
    {
        Collections.sort(mascotas, new Comparator<Mascota>() {
            @Override
            public int compare(Mascota mascota1, Mascota mascota2) {
                return new Integer(mascota2.getLikes()).compareTo(new Integer(mascota1.getLikes()));
            }
        });

        ArrayList<Mascota> favoritas = new ArrayList<Mascota>();
        for(int i = 0; i <= 4; i++)
        {
            favoritas.add(new Mascota(mascotas.get(i).getImagen(), mascotas.get(i).getNombre(), mascotas.get(i).getLikes()));
        }

        MascotasAdaptador adapter = new MascotasAdaptador(favoritas, this);
        rvMascotasFavoritas.setAdapter(adapter);
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
