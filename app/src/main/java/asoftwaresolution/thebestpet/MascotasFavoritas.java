package asoftwaresolution.thebestpet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

        Bundle bundle = getIntent().getExtras();
        mascotas = (ArrayList<Mascota>) bundle.getSerializable(getResources().getString(R.string.pmascotas));

        miActionBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MascotasFavoritas.this, ListadoMascotas.class);
                intent.putExtra(getResources().getString(R.string.pmascotas), mascotas);
                startActivity(intent);
                finish();
            }
        });

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
}
