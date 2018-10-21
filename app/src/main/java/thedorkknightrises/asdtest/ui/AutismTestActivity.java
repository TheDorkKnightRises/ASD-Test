package thedorkknightrises.asdtest.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import thedorkknightrises.asdtest.R;
import thedorkknightrises.asdtest.util.RestClient;

public class AutismTestActivity extends AppCompatActivity {

    RadioGroup q_hand_finger_mannerism_rg, q_imagination_rg, q_echolalia_rg, q_social_overtures_rg, q_self_injurious_behavior_rg, q_shared_enjoyment_rg, q_tantrums_rg, q_eye_contact_rg;
    SeekBar q_anxiety_seekbar;
    TextView anxiety_value;
    Bundle details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autism_test);

        RequestParams params = new RequestParams();
        details = getIntent().getExtras();
        if (details == null) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            params.put("is_male", details.getInt("is_male"));
        }

        q_hand_finger_mannerism_rg = findViewById(R.id.radio_group_q_hand_finger_mannerism);
        q_imagination_rg = findViewById(R.id.radio_group_q_imagination);
        q_echolalia_rg = findViewById(R.id.radio_group_q_echolalia);
        q_social_overtures_rg = findViewById(R.id.radio_group_q_social_overtures);
        q_self_injurious_behavior_rg = findViewById(R.id.radio_group_q_self_injurious_behavior);
        q_shared_enjoyment_rg = findViewById(R.id.radio_group_q_shared_enjoyment);
        q_tantrums_rg = findViewById(R.id.radio_group_q_tantrums);
        q_eye_contact_rg = findViewById(R.id.radio_group_q_eye_contact);

        q_anxiety_seekbar = findViewById(R.id.anxiety_seekbar);
        anxiety_value = findViewById(R.id.anxiety_value);
        q_anxiety_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                anxiety_value.setText(String.valueOf(progress + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        findViewById(R.id.startButton).setOnLongClickListener((view) -> {
            startActivity(new Intent(AutismTestActivity.this, ConfigurationActivity.class));
            return true;
        });

        findViewById(R.id.startButton).setOnClickListener((view) -> {
            boolean error = false;

            if (q_hand_finger_mannerism_rg.getCheckedRadioButtonId() == -1 || q_imagination_rg.getCheckedRadioButtonId() == -1 ||
                    q_echolalia_rg.getCheckedRadioButtonId() == -1 || q_social_overtures_rg.getCheckedRadioButtonId() == -1 ||
                    q_self_injurious_behavior_rg.getCheckedRadioButtonId() == -1 || q_shared_enjoyment_rg.getCheckedRadioButtonId() == -1 ||
                    q_tantrums_rg.getCheckedRadioButtonId() == -1 || q_eye_contact_rg.getCheckedRadioButtonId() == -1) {
                error = true;
                Toast.makeText(this, R.string.invalid_input_prompt, Toast.LENGTH_SHORT).show();
            } else {

                RadioButton selectedItem = findViewById(q_hand_finger_mannerism_rg.getCheckedRadioButtonId());
                params.put("A1_Score",
                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 2 : (selectedItem.getText().toString().equals(getString(R.string.neutral))) ? 1 : 0);

                selectedItem = findViewById(q_imagination_rg.getCheckedRadioButtonId());
                params.put("A2_Score",
                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 2 : (selectedItem.getText().toString().equals(getString(R.string.neutral))) ? 1 : 0);

                selectedItem = findViewById(q_echolalia_rg.getCheckedRadioButtonId());
                params.put("A3_Score",
                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 2 : (selectedItem.getText().toString().equals(getString(R.string.neutral))) ? 1 : 0);

                selectedItem = findViewById(q_social_overtures_rg.getCheckedRadioButtonId());
                params.put("A4_Score",
                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 2 : (selectedItem.getText().toString().equals(getString(R.string.neutral))) ? 1 : 0);

                selectedItem = findViewById(q_self_injurious_behavior_rg.getCheckedRadioButtonId());
                params.put("A5_Score",
                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 2 : (selectedItem.getText().toString().equals(getString(R.string.neutral))) ? 1 : 0);

                selectedItem = findViewById(q_shared_enjoyment_rg.getCheckedRadioButtonId());
                params.put("A6_Score",
                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 2 : (selectedItem.getText().toString().equals(getString(R.string.neutral))) ? 1 : 0);

                selectedItem = findViewById(q_tantrums_rg.getCheckedRadioButtonId());
                params.put("A7_Score",
                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 2 : (selectedItem.getText().toString().equals(getString(R.string.neutral))) ? 1 : 0);

                selectedItem = findViewById(q_eye_contact_rg.getCheckedRadioButtonId());
                params.put("A8_Score",
                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 2 : (selectedItem.getText().toString().equals(getString(R.string.neutral))) ? 1 : 0);

                params.put("anxiety", q_anxiety_seekbar.getProgress());

            }

            if (!error) {
                ProgressDialog progressDialog = new ProgressDialog(AutismTestActivity.this);
                String path = "autismtest";
                RestClient.get(path, null, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        progressDialog.setCancelable(false);
                        progressDialog.setMessage(getString(R.string.please_wait));
                        progressDialog.show();

                        Toast.makeText(AutismTestActivity.this, RestClient.getBaseUrl() + path, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String response) {
                        super.onSuccess(statusCode, headers, response);

                        progressDialog.dismiss();
                        Toast.makeText(AutismTestActivity.this,
                                (response != null) ? response : "No response from server",
                                Toast.LENGTH_SHORT).show();
                        // parse and show the results
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);

                        progressDialog.dismiss();
                        Toast.makeText(AutismTestActivity.this,
                                (response != null) ? response.toString() : "No response from server",
                                Toast.LENGTH_SHORT).show();
                        // parse and show the results
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
                        super.onFailure(statusCode, headers, response, throwable);

                        progressDialog.dismiss();
                        Toast.makeText(AutismTestActivity.this,
                                (response != null) ? response : "No response from server",
                                Toast.LENGTH_SHORT).show();
                        // parse and show the results
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                        super.onFailure(statusCode, headers, throwable, response);

                        progressDialog.dismiss();
                        Toast.makeText(AutismTestActivity.this,
                                (response != null) ? response.toString() : "No response from server",
                                Toast.LENGTH_SHORT).show();
                        // parse and show the results
                    }
                });
            }
        });

    }
}
