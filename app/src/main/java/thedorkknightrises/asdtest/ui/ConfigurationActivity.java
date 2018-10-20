package thedorkknightrises.asdtest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import thedorkknightrises.asdtest.R;
import thedorkknightrises.asdtest.util.RestClient;

public class ConfigurationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        findViewById(R.id.apply_button).setOnClickListener((view) -> {
            String url = ((EditText) findViewById(R.id.url_edittext)).getText().toString().trim();
            if (!"".equals(url)) {
                RestClient.setBaseUrl(url);
                getPreferences(MODE_PRIVATE).edit().putString("debug_server_url", url).apply();
            }
        });
    }
}
