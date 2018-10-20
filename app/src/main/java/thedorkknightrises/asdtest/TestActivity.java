package thedorkknightrises.asdtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {

    RadioGroup q1_rg, q2_rg, q3_rg, q4_rg, q5_rg, q6_rg, q7_rg, q8_rg, q9_rg, q10_rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Bundle details = getIntent().getExtras();
        if (details == null) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            finish();
        }

        q1_rg = findViewById(R.id.radio_group_q1);
        q2_rg = findViewById(R.id.radio_group_q2);
        q3_rg = findViewById(R.id.radio_group_q3);
        q4_rg = findViewById(R.id.radio_group_q4);
        q5_rg = findViewById(R.id.radio_group_q5);
        q6_rg = findViewById(R.id.radio_group_q6);
        q7_rg = findViewById(R.id.radio_group_q7);
        q8_rg = findViewById(R.id.radio_group_q8);
        q9_rg = findViewById(R.id.radio_group_q9);
        q10_rg = findViewById(R.id.radio_group_q10);

    }
}
