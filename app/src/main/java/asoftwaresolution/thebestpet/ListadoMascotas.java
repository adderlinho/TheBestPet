package asoftwaresolution.thebestpet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListadoMascotas extends AppCompatActivity {

    ArrayList<Mascota> mascotas;
    RecyclerView rvMascotas;
    ImageButton starActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_mascotas);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        starActionBar       = (ImageButton) findViewById(R.id.starActionBar);
        setSupportActionBar(miActionBar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        rvMascotas = (RecyclerView) findViewById(R.id.rvMascotas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(linearLayoutManager);
        inicializarListaContactos();
        inicializarAdaptador(mascotas);

        starActionBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListadoMascotas.this, MascotasFavoritas.class);
                intent.putExtra(getResources().getString(R.string.pmascotas), mascotas);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            mascotas = (ArrayList<Mascota>) bundle.getSerializable(getResources().getString(R.string.pmascotas));

            Collections.sort(mascotas, new Comparator<Mascota>() {
                @Override
                public int compare(Mascota mascota1, Mascota mascota2) {
                    return new Integer(mascota1.getLikes()).compareTo(new Integer(mascota2.getLikes()));
                }
            });

            inicializarAdaptador(mascotas);
        }

    }

    public void inicializarAdaptador(ArrayList<Mascota> mascotas)
    {
        MascotasAdaptador adapter = new MascotasAdaptador(mascotas, this);
        rvMascotas.setAdapter(adapter);
    }

    public void inicializarListaContactos()
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

