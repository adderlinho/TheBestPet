package asoftwaresolution.thebestpet;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import asoftwaresolution.thebestpet.Session.SessionManager;
import asoftwaresolution.thebestpet.fragment.MascotaPerfil;
import asoftwaresolution.thebestpet.restApi.ConstantesRestApi;
import asoftwaresolution.thebestpet.restApi.EndpointsApi;

public class ActivityConfiguracion extends AppCompatActivity {

    private Button btnEnviar;
    private TextInputLayout textInputLayoutUsername;
    private SessionManager manager;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        manager = new SessionManager();
        context = this;

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        miActionBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityConfiguracion.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnEnviar               = (Button) findViewById(R.id.btnEnviar);
        textInputLayoutUsername = (TextInputLayout) findViewById(R.id.textInputLayoutUsername);

        manager.setPreferences(context, "KEY_ID_USER_INSTAGRAM", "");
        manager.setPreferences(context, "KEY_ID_USER_FIREBASE", "");
        manager.setPreferences(context, "KEY_USERNAME", "");

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.setPreferences(context, "KEY_USERNAME", textInputLayoutUsername.getEditText().getText().toString());
                Intent intent = new Intent(ActivityConfiguracion.this, MainActivity.class);
                intent.putExtra(getResources().getString(R.string.username_instagram), textInputLayoutUsername.getEditText().getText().toString());
                startActivity(intent);
            }
        });
    }
}
