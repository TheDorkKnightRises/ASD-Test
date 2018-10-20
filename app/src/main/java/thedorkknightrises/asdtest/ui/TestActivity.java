package thedorkknightrises.asdtest.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import thedorkknightrises.asdtest.R;
import thedorkknightrises.asdtest.util.RestClient;

public class TestActivity extends AppCompatActivity {

    RadioGroup q1_rg, q2_rg, q3_rg, q4_rg, q5_rg, q6_rg, q7_rg, q8_rg, q9_rg, q10_rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        RequestParams params = new RequestParams();
        Bundle details = getIntent().getExtras();
        if (details == null) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            params.put("age", details.getInt("age"));
            params.put("is_male", details.getInt("is_male"));
            params.put("jaundice", details.getInt("jaundice"));
            params.put("family_autism", details.getInt("family_autism"));
            params.put("country", details.getString("country"));
            params.put("ethnicity", details.getString("ethnicity"));
            params.put("user", details.getString("user"));
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

        findViewById(R.id.startButton).setOnLongClickListener((view) -> {
            startActivity(new Intent(TestActivity.this, ConfigurationActivity.class));
            return true;
        });

        findViewById(R.id.startButton).setOnClickListener((view) -> {
            boolean error = false;

            if (q1_rg.getCheckedRadioButtonId() == -1 || q2_rg.getCheckedRadioButtonId() == -1 ||
                    q3_rg.getCheckedRadioButtonId() == -1 || q4_rg.getCheckedRadioButtonId() == -1 ||
                    q5_rg.getCheckedRadioButtonId() == -1 || q6_rg.getCheckedRadioButtonId() == -1 ||
                    q7_rg.getCheckedRadioButtonId() == -1 || q8_rg.getCheckedRadioButtonId() == -1 ||
                    q9_rg.getCheckedRadioButtonId() == -1 || q10_rg.getCheckedRadioButtonId() == -1) {
                error = true;
                Toast.makeText(this, R.string.invalid_input_prompt, Toast.LENGTH_SHORT).show();
            } else {

                RadioButton selectedItem = findViewById(q1_rg.getCheckedRadioButtonId());
                params.put("A1_Score",
                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 1 : 0);

                selectedItem = findViewById(q2_rg.getCheckedRadioButtonId());
                params.put("A2_Score",
                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 1 : 0);

                selectedItem = findViewById(q3_rg.getCheckedRadioButtonId());
                params.put("A3_Score",
                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 1 : 0);

                selectedItem = findViewById(q4_rg.getCheckedRadioButtonId());
                params.put("A4_Score",
                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 1 : 0);

                selectedItem = findViewById(q5_rg.getCheckedRadioButtonId());
                params.put("A5_Score",
                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 1 : 0);

                selectedItem = findViewById(q6_rg.getCheckedRadioButtonId());
                params.put("A6_Score",
                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 1 : 0);

                selectedItem = findViewById(q7_rg.getCheckedRadioButtonId());
                params.put("A7_Score",
                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 1 : 0);

                selectedItem = findViewById(q8_rg.getCheckedRadioButtonId());
                params.put("A8_Score",
                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 1 : 0);

                selectedItem = findViewById(q9_rg.getCheckedRadioButtonId());
                params.put("A9_Score",
                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 1 : 0);

                selectedItem = findViewById(q10_rg.getCheckedRadioButtonId());
                params.put("A10_Score",
                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 1 : 0);
            }

            if (!error) {
                ProgressDialog progressDialog = new ProgressDialog(TestActivity.this);
                RestClient.post("asdtest", null, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        progressDialog.setCancelable(false);
                        progressDialog.setMessage(getString(R.string.please_wait));
                        progressDialog.show();

                        Log.d("URL", RestClient.getBaseUrl());
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String response) {
                        super.onSuccess(statusCode, headers, response);

                        progressDialog.dismiss();
                        Toast.makeText(TestActivity.this,
                                (response != null) ? response : "No response from server",
                                Toast.LENGTH_SHORT).show();
                        // parse and show the results
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);

                        progressDialog.dismiss();
                        Toast.makeText(TestActivity.this,
                                (response != null) ? response.toString() : "No response from server",
                                Toast.LENGTH_SHORT).show();
                        // parse and show the results
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
                        super.onFailure(statusCode, headers, response, throwable);

                        progressDialog.dismiss();
                        Toast.makeText(TestActivity.this,
                                (response != null) ? response : "No response from server",
                                Toast.LENGTH_SHORT).show();
                        // parse and show the results
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                        super.onFailure(statusCode, headers, throwable, response);

                        progressDialog.dismiss();
                        Toast.makeText(TestActivity.this,
                                (response != null) ? response.toString() : "No response from server",
                                Toast.LENGTH_SHORT).show();
                        // parse and show the results
                    }
                });
            }
        });

    }
}
