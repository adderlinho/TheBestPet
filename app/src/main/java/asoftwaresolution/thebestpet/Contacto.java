package asoftwaresolution.thebestpet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Contacto extends AppCompatActivity {

    private TextInputEditText form_username;
    private TextInputEditText form_email;
    private TextInputEditText form_password_email;
    private TextInputEditText form_message;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        form_username = (TextInputEditText) findViewById(R.id.form_username);
        form_email = (TextInputEditText) findViewById(R.id.form_email);
        form_email = (TextInputEditText) findViewById(R.id.form_password_email);
        form_message = (TextInputEditText) findViewById(R.id.form_message);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);

        miActionBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Contacto.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(form_username.getText().toString().isEmpty() || form_email.getText().toString().isEmpty() || form_message.getText().toString().isEmpty())
                {
                    Snackbar.make(view, getResources().getString(R.string.txt_mensaje_error), Snackbar.LENGTH_LONG)
                            .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                            .show();
                }
                else
                {
                    //Creating SendMail object
                    SendMail sm = new SendMail(Contacto.this, form_email.getText().toString(), form_password_email.getText().toString(), form_username.getText().toString(), form_message.getText().toString());

                    //Executing sendmail to send email
                    sm.execute();

                    form_username.setText("");
                    form_email.setText("");
                    form_password_email.setText("");
                    form_message.setText("");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
