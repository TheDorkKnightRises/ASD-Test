package thedorkknightrises.asdtest.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import thedorkknightrises.asdtest.R;
import thedorkknightrises.asdtest.util.RestClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        if (preferences.contains("debug_server_url")) {
            RestClient.setBaseUrl(preferences.getString("debug_server_url", ""));
        }

        findViewById(R.id.startButton).setOnClickListener((view) -> startActivity(
                new Intent(MainActivity.this, PersonalDetailsActivity.class)));
    }
}
