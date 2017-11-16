package asoftwaresolution.thebestpet;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import asoftwaresolution.thebestpet.adaptadores.PageAdapter;
import asoftwaresolution.thebestpet.db.ConstructorMascotas;
import asoftwaresolution.thebestpet.fragment.MascotaPerfil;
import asoftwaresolution.thebestpet.fragment.MascotasListado;
import asoftwaresolution.thebestpet.pojo.Usuario;
import asoftwaresolution.thebestpet.restApi.ConstantesRestApi;
import asoftwaresolution.thebestpet.restApi.EndpointsApi;
import asoftwaresolution.thebestpet.restApi.adapter.RestApiAdapter;
import asoftwaresolution.thebestpet.restApi.model.FirebaseResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageButton starActionBar;
    private ArrayList<Usuario> usuario = new ArrayList<>();
    private ConstructorMascotas constructorMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        starActionBar       = (ImageButton) findViewById(R.id.starActionBar);
        setUpViewPager();

        if(toolbar != null)
        {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        starActionBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MascotasFavoritas.class);
                startActivity(intent);
            }
        });

        constructorMascotas = new ConstructorMascotas(this);
        usuario = constructorMascotas.obtenerUsuarioRegistrado();
        if(usuario.size() == 0)
        {
            Log.i("USUARIO NO REGISTRADO: ", String.valueOf(usuario.size()));
        }
        else
        {
            ConstantesRestApi.KEY_ID_USER_INSTAGRAM = usuario.get(0).getId_instagram();
            ConstantesRestApi.KEY_ID_USER_FIREBASE = usuario.get(0).getId_firebase();
            ConstantesRestApi.KEY_USERNAME = usuario.get(0).getUsername();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.mContactos:
                Intent intentAbout = new Intent(MainActivity.this, Contacto.class);
                startActivity(intentAbout);
                break;
            case R.id.mAcercaDe:
                Intent intentSettings = new Intent(MainActivity.this, Acercade.class);
                startActivity(intentSettings);
                break;
            case R.id.mConfigurar:
                Intent intentConfig = new Intent(MainActivity.this, ActivityConfiguracion.class);
                startActivity(intentConfig);
                break;
            case R.id.mNotificaciones:
                enviarRegistroFireBase();
                break;
            case R.id.mInformacion:
                Intent intentInfo = new Intent(MainActivity.this, Informacion.class);
                startActivity(intentInfo);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Fragment> agregarFragments()
    {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MascotasListado());
        fragments.add(new MascotaPerfil());
        return fragments;
    }

    private void setUpViewPager()
    {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_house_dog);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dog_face);
    }

    private void enviarRegistroFireBase()
    {
        if(ConstantesRestApi.KEY_ID_USER_INSTAGRAM != "" && ConstantesRestApi.KEY_USERNAME != "")
        {
            if(ConstantesRestApi.KEY_ID_USER_FIREBASE == "")
            {
                String token = FirebaseInstanceId.getInstance().getToken();
                RestApiAdapter restApiAdapter = new RestApiAdapter();
                EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiHeroku();
                Call<FirebaseResponse> firebaseResponseCall = endpointsApi.registrarTokenID(token, ConstantesRestApi.KEY_ID_USER_INSTAGRAM);
                firebaseResponseCall.enqueue(new Callback<FirebaseResponse>() {
                    @Override
                    public void onResponse(Call<FirebaseResponse> call, Response<FirebaseResponse> response) {
                        FirebaseResponse firebaseResponse = response.body();
                        constructorMascotas.insertarIdFirebaseUsuarioDB(firebaseResponse.getId());
                        Log.d("ID_USUARIO_FIREBASE", firebaseResponse.getId());
                        Log.d("ID_DISPOSITIVO", firebaseResponse.getId_dispositivo());
                        Log.d("ID_USUARIO_INSTAGRAM", firebaseResponse.getId_usuario_instagram());
                    }

                    @Override
                    public void onFailure(Call<FirebaseResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "!Algo pasó en la conexión! Intenta de nuevo.", Toast.LENGTH_LONG).show();
                        Log.i("FALLO LA CONEXIÓN", t.toString());
                    }
                });
            }
            else
            {
                Toast.makeText(this, "Su dispositivo ya está listo para recibir notificaciones.", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Intent intent = new Intent(this, ActivityConfiguracion.class);
            startActivity(intent);
        }
    }
}
