package asoftwaresolution.thebestpet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import asoftwaresolution.thebestpet.Session.SessionManager;
import asoftwaresolution.thebestpet.presentador.MascotaPerfilFragmentPresenter;
import asoftwaresolution.thebestpet.restApi.ConstantesRestApi;

import static java.security.AccessController.getContext;

public class Informacion extends AppCompatActivity {

    private TextView tvUsernameInfo;
    private TextView tvIdInstagramInfo;
    private TextView tvIdFirebaseInfo;
    private SessionManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        manager = new SessionManager();

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tvUsernameInfo =    (TextView) findViewById(R.id.tvUsernameInfo);
        tvIdInstagramInfo = (TextView) findViewById(R.id.tvIdInstagramInfo);
        tvIdFirebaseInfo =  (TextView) findViewById(R.id.tvIdFirebaseInfo);

        miActionBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Informacion.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        if(manager.getPreferences(getApplicationContext(),"KEY_USERNAME") != "")
        {
            tvUsernameInfo.setText("Username: " + manager.getPreferences(getApplicationContext(),"KEY_USERNAME"));
            tvIdInstagramInfo.setText("Instagram Id: " + manager.getPreferences(getApplicationContext(),"KEY_ID_USER_INSTAGRAM"));
            tvIdFirebaseInfo.setText("Firebase Id: " + manager.getPreferences(getApplicationContext(),"KEY_ID_USER_FIREBASE"));
        }
        else
        {
            Intent intent = new Intent(this, ActivityConfiguracion.class);
            startActivity(intent);
            finish();
        }
    }
}
