package asoftwaresolution.thebestpet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import asoftwaresolution.thebestpet.presentador.MascotaPerfilFragmentPresenter;
import asoftwaresolution.thebestpet.restApi.ConstantesRestApi;

public class Informacion extends AppCompatActivity {

    private TextView tvUsernameInfo;
    private TextView tvIdInstagramInfo;
    private TextView tvIdFirebaseInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

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

        if(ConstantesRestApi.KEY_USERNAME != "")
        {
            tvUsernameInfo.setText("Username: " + ConstantesRestApi.KEY_USERNAME.toString());
            tvIdInstagramInfo.setText("Instagram Id: " + ConstantesRestApi.KEY_ID_USER_INSTAGRAM.toString());
            tvIdFirebaseInfo.setText("Firebase Id: " + ConstantesRestApi.KEY_ID_USER_FIREBASE.toString());
        }
        else
        {
            Intent intent = new Intent(this, ActivityConfiguracion.class);
            startActivity(intent);
        }
    }
}
