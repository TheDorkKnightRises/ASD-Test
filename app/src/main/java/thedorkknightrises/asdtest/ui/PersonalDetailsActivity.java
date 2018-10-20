package thedorkknightrises.asdtest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import thedorkknightrises.asdtest.R;

public class PersonalDetailsActivity extends AppCompatActivity {

    String[] ethnicities = {"?", "Middle Eastern", "Pasifika", "White-European", "South Asian",
            "Black", "Asian", "Turkish", "Others", "Latino", "Hispanic"};
    String[] countries = {"Afghanistan", "Albania", "AmericanSamoa", "Angola", "Anguilla",
            "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas",
            "Bahrain", "Bangladesh", "Belgium", "Bhutan", "Bolivia", "Brazil", "Bulgaria",
            "Burundi", "Canada", "Chile", "China", "Comoros", "Costa Rica", "Croatia", "Cyprus",
            "Czech Republic", "Ecuador", "Egypt", "Ethiopia", "Europe", "Finland", "France",
            "Georgia", "Germany", "Ghana", "Greenland", "Hong Kong", "Iceland", "India",
            "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Italy", "Japan", "Jordan",
            "Kazakhstan", "Kuwait", "Latvia", "Lebanon", "Libya", "Malaysia", "Malta", "Mexico",
            "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Norway",
            "Oman", "Pakistan", "Philippines", "Portugal", "Qatar", "Romania", "Russia",
            "Saudi Arabia", "Serbia", "Sierra Leone", "South Africa", "South Korea", "Spain",
            "Sri Lanka", "Sweden", "Syria", "Tonga", "Turkey", "U.S. Outlying Islands", "Ukraine",
            "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Viet Nam"};
    String[] users = {"?", "Self", "Parent", "Relative", "Health care professional", "Others"};

    EditText age_edittext;
    Spinner ethnicity_spinner, country_spinner, user_spinner;
    RadioGroup gender_rg, jaundice_rg, family_autism_rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        age_edittext = findViewById(R.id.age_edittext);
        ethnicity_spinner = findViewById(R.id.spinner_ethnicity);
        country_spinner = findViewById(R.id.spinner_country);
        user_spinner = findViewById(R.id.spinner_user);
        gender_rg = findViewById(R.id.radio_group_gender);
        jaundice_rg = findViewById(R.id.radio_group_jaundice);
        family_autism_rg = findViewById(R.id.radio_group_family_autism);

        ethnicity_spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, ethnicities));
        country_spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, countries));
        user_spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, users));

        findViewById(R.id.startButton).setOnClickListener((view) -> {
            Bundle extras = new Bundle();
            boolean error = false;
            if ("".equals(age_edittext.getText().toString().trim())) {
                error = true;
                age_edittext.setError(getString(R.string.invalid_input_prompt));
                age_edittext.requestFocus();
            } else {
                extras.putInt("age", Integer.parseInt(age_edittext.getText().toString().trim()));
            }

            if (gender_rg.getCheckedRadioButtonId() == -1) {
                error = true;
                TextView header = findViewById(R.id.text_gender);
                header.setError(getString(R.string.invalid_input_prompt));
                header.requestFocus();
                Toast.makeText(this, R.string.invalid_input_prompt, Toast.LENGTH_SHORT).show();
            } else {
                TextView header = findViewById(R.id.text_gender);
                header.setError(null);
                RadioButton selectedItem = findViewById(gender_rg.getCheckedRadioButtonId());
                extras.putInt("is_male",
                        (selectedItem.getText().toString().equals(getString(R.string.male))) ? 1 : 0);
            }

            if (jaundice_rg.getCheckedRadioButtonId() == -1) {
                error = true;
                TextView header = findViewById(R.id.text_jaundice);
                header.setError(getString(R.string.invalid_input_prompt));
                header.requestFocus();
                Toast.makeText(this, R.string.invalid_input_prompt, Toast.LENGTH_SHORT).show();
            } else {
                TextView header = findViewById(R.id.text_jaundice);
                header.setError(null);
                RadioButton selectedItem = findViewById(gender_rg.getCheckedRadioButtonId());
                extras.putInt("jaundice",
                        (selectedItem.getText().toString().equals(getString(R.string.yes))) ? 1 : 0);
            }

            if (family_autism_rg.getCheckedRadioButtonId() == -1) {
                error = true;
                TextView header = findViewById(R.id.text_family_autism);
                header.setError(getString(R.string.invalid_input_prompt));
                header.requestFocus();
                Toast.makeText(this, R.string.invalid_input_prompt, Toast.LENGTH_SHORT).show();
            } else {
                TextView header = findViewById(R.id.text_family_autism);
                header.setError(null);
                RadioButton selectedItem = findViewById(gender_rg.getCheckedRadioButtonId());
                extras.putInt("family_autism",
                        (selectedItem.getText().toString().equals(getString(R.string.yes))) ? 1 : 0);
            }

            if (!error) {
                extras.putString("ethnicity", ethnicity_spinner.getSelectedItem().toString());
                extras.putString("country", country_spinner.getSelectedItem().toString());
                extras.putString("user", user_spinner.getSelectedItem().toString());

                Intent intent = new Intent(PersonalDetailsActivity.this, TestActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}
