package thedorkknightrises.asdtest.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wenchao.cardstack.CardStack;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import thedorkknightrises.asdtest.R;
import thedorkknightrises.asdtest.util.RestClient;

public class AutismTestActivity extends AppCompatActivity implements CardStack.CardEventListener {


    Bundle details;
    CardStackView cardStack;
    CardAdapter cardAdapter;
    CardStackLayoutManager manager;
    static int currentCard=1;
    View swipe;
    GestureDetector detector;
    float x1,x2;
    static boolean pageFlag=true;
    RequestParams params = new RequestParams();

    static HashMap<Integer,Integer> answers=new HashMap<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autism_test);

        swipe = findViewById(R.id.trial);

        details = getIntent().getExtras();
        if (details == null) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            params.put("is_male", details.getInt("is_male"));
            params.put("age_months", details.getInt("age") * 12);
        }

        cardAdapter=new CardAdapter(getApplicationContext(),R.layout.card_layout);
        AdapterPages adapterPages = new AdapterPages(this,cardAdapter);

        manager = new CardStackLayoutManager(this);
        cardStack=(CardStackView) findViewById(R.id.cardStack);
        cardStack.setLayoutManager(manager);

        cardStack.setAdapter(adapterPages);
        manager.setCanScrollHorizontal(false);
        manager.setCanScrollVertical(false);

        cardAdapter.add("Question 1");
        cardAdapter.add("Question 2");
        cardAdapter.add("Question 3");
        cardAdapter.add("Question 4");
        cardAdapter.add("Question 5");
        cardAdapter.add("Question 6");
        cardAdapter.add("Question 7");
        cardAdapter.add("Question 8");
        cardAdapter.add("Question 9");

        Toast.makeText(this, "Swipe Left or Right", Toast.LENGTH_SHORT).show();

//        cardStack.setListener(this);

//        q_hand_finger_mannerism_rg = findViewById(R.id.radio_group_q_hand_finger_mannerism);
//        q_imagination_rg = findViewById(R.id.radio_group_q_imagination);
//        q_echolalia_rg = findViewById(R.id.radio_group_q_echolalia);
//        q_social_overtures_rg = findViewById(R.id.radio_group_q_social_overtures);
//        q_self_injurious_behavior_rg = findViewById(R.id.radio_group_q_self_injurious_behavior);
//        q_shared_enjoyment_rg = findViewById(R.id.radio_group_q_shared_enjoyment);
//        q_tantrums_rg = findViewById(R.id.radio_group_q_tantrums);
//        q_eye_contact_rg = findViewById(R.id.radio_group_q_eye_contact);
//
//        q_anxiety_seekbar = findViewById(R.id.anxiety_seekbar);
//        anxiety_value = findViewById(R.id.anxiety_value);
//        q_anxiety_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                anxiety_value.setText(String.valueOf(progress + 1));
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
//        findViewById(R.id.startButton).setOnLongClickListener((view) -> {
//            startActivity(new Intent(AutismTestActivity.this, ConfigurationActivity.class));
//            return true;
//        });
//
//        findViewById(R.id.startButton).setOnClickListener((view) -> {
//            boolean error = false;
//
//            if (q_hand_finger_mannerism_rg.getCheckedRadioButtonId() == -1 || q_imagination_rg.getCheckedRadioButtonId() == -1 ||
//                    q_echolalia_rg.getCheckedRadioButtonId() == -1 || q_social_overtures_rg.getCheckedRadioButtonId() == -1 ||
//                    q_self_injurious_behavior_rg.getCheckedRadioButtonId() == -1 || q_shared_enjoyment_rg.getCheckedRadioButtonId() == -1 ||
//                    q_tantrums_rg.getCheckedRadioButtonId() == -1 || q_eye_contact_rg.getCheckedRadioButtonId() == -1) {
//                error = true;
//                Toast.makeText(this, R.string.invalid_input_prompt, Toast.LENGTH_SHORT).show();
//            } else {
//
//                RadioButton selectedItem = findViewById(q_hand_finger_mannerism_rg.getCheckedRadioButtonId());
//                params.put("A1_Score",
//                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 2 : (selectedItem.getText().toString().equals(getString(R.string.neutral))) ? 1 : 0);
//
//                selectedItem = findViewById(q_imagination_rg.getCheckedRadioButtonId());
//                params.put("A2_Score",
//                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 2 : (selectedItem.getText().toString().equals(getString(R.string.neutral))) ? 1 : 0);
//
//                selectedItem = findViewById(q_echolalia_rg.getCheckedRadioButtonId());
//                params.put("A3_Score",
//                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 2 : (selectedItem.getText().toString().equals(getString(R.string.neutral))) ? 1 : 0);
//
//                selectedItem = findViewById(q_social_overtures_rg.getCheckedRadioButtonId());
//                params.put("A4_Score",
//                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 0 : (selectedItem.getText().toString().equals(getString(R.string.neutral))) ? 1 : 2);
//
//                selectedItem = findViewById(q_self_injurious_behavior_rg.getCheckedRadioButtonId());
//                params.put("A5_Score",
//                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 2 : (selectedItem.getText().toString().equals(getString(R.string.neutral))) ? 1 : 0);
//
//                selectedItem = findViewById(q_shared_enjoyment_rg.getCheckedRadioButtonId());
//                params.put("A6_Score",
//                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 0 : (selectedItem.getText().toString().equals(getString(R.string.neutral))) ? 1 : 2);
//
//                selectedItem = findViewById(q_tantrums_rg.getCheckedRadioButtonId());
//                params.put("A7_Score",
//                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 2 : (selectedItem.getText().toString().equals(getString(R.string.neutral))) ? 1 : 0);
//
//                selectedItem = findViewById(q_eye_contact_rg.getCheckedRadioButtonId());
//                params.put("A8_Score",
//                        (selectedItem.getText().toString().equals(getString(R.string.agree))) ? 2 : (selectedItem.getText().toString().equals(getString(R.string.neutral))) ? 1 : 0);
//
//                params.put("anxiety", q_anxiety_seekbar.getProgress());
//
//            }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//            //After Button click
//
//            if (!error) {
//                ProgressDialog progressDialog = new ProgressDialog(AutismTestActivity.this);
//                String path = "autismtest";
//
//                RestClient.post(path, null, params, new JsonHttpResponseHandler() {
//                    @Override
//                    public void onStart() {
//                        super.onStart();
//                        progressDialog.setCancelable(false);
//                        progressDialog.setMessage(getString(R.string.please_wait));
//                        progressDialog.show();
//                    }
//
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, String response) {
//                        progressDialog.dismiss();
//                        if (response == null) {
//                            Toast.makeText(AutismTestActivity.this,
//                                    "No response from server",
//                                    Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//
//                        final TextView message = new TextView(AutismTestActivity.this);
//                        final SpannableString s =
//                                new SpannableString((response.equals("true")) ? getText(R.string.autism_positive) : (response.equals("false")) ? getText(R.string.autism_negative) : response);
//                        Linkify.addLinks(s, Linkify.WEB_URLS);
//                        message.setText(s);
//                        message.setPadding(32, 32, 32, 32);
//                        message.setMovementMethod(LinkMovementMethod.getInstance());
//
//                        AlertDialog.Builder alert = new AlertDialog.Builder(AutismTestActivity.this)
//                                .setView(message)
//                                .setNegativeButton(R.string.exit, (dialog, which) -> {
//                                    finishAffinity();
//                                })
//                                .setCancelable(false);
//
//                        if (response.equals("true")) {
//                            alert.show();
//                        } else {
//                            alert.setPositiveButton(R.string.cont, (dialog, which) -> {
//                                Intent intent = new Intent(AutismTestActivity.this, ASDTestActivity.class);
//                                intent.putExtras(details);
//                                startActivity(intent);
//                            }).show();
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
//                        progressDialog.dismiss();
//                        if (response == null) {
//                            Toast.makeText(AutismTestActivity.this,
//                                    "No response from server",
//                                    Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//
//                        final TextView message = new TextView(AutismTestActivity.this);
//                        final SpannableString s =
//                                new SpannableString((response.equals("true")) ? getText(R.string.autism_positive) : (response.equals("false")) ? getText(R.string.autism_negative) : response);
//                        Linkify.addLinks(s, Linkify.WEB_URLS);
//                        message.setText(s);
//                        message.setPadding(32, 32, 32, 32);
//                        message.setMovementMethod(LinkMovementMethod.getInstance());
//
//                        AlertDialog.Builder alert = new AlertDialog.Builder(AutismTestActivity.this)
//                                .setView(message)
//                                .setNegativeButton(R.string.exit, (dialog, which) -> {
//                                    finishAffinity();
//                                })
//                                .setCancelable(false);
//
//                        if (response.equals("true")) {
//                            alert.show();
//                        } else {
//                            alert.setPositiveButton(R.string.cont, (dialog, which) -> {
//                                Intent intent = new Intent(AutismTestActivity.this, ASDTestActivity.class);
//                                intent.putExtras(details);
//                                startActivity(intent);
//                            }).show();
//                        }
//                    }
//
//                });
//            }
//        });



        GestureDetector.OnGestureListener listener = new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                if (motionEvent.getRawX() < motionEvent1.getRawX()) {
                    manager.setCanScrollHorizontal(true);

                    if(pageFlag) {
                        currentCard++;
                        cardStack.swipe();
                    }
                    else
                        Toast.makeText(AutismTestActivity.this, "Please enter a valid answer", Toast.LENGTH_SHORT).show();

                    if(currentCard>=10) {
                        //TODO Call Submit Function and pass answers (HashMap)to it
                        Toast.makeText(AutismTestActivity.this, "Submitting...", Toast.LENGTH_SHORT).show();
                        submitAnswers();
                    }
                }
                else {
                    manager.setCanScrollHorizontal(true);
                    currentCard--;
                    cardStack.rewind();
                }
                return true;
            }
        };

        detector = new GestureDetector(this,listener);

        List<Direction> d = new ArrayList<>();
        manager.setDirections(d);


        swipe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return  detector.onTouchEvent(motionEvent);
//              https://www.techrepublic.com/blog/software-engineer/use-androids-gesture-detector-to-translate-a-swipe-into-an-event/

            }
        });


    }


    @Override
    public boolean swipeEnd(int i, float v) {
        return (v>300)?true:false;
    }

    @Override
    public boolean swipeStart(int i, float v) {
        return false;
    }

    @Override
    public boolean swipeContinue(int i, float v, float v1) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        return true;
    }

    @Override
    public void discarded(int i, int i1) {
    }

    @Override
    public void topCardTapped() {
    }


    void submitAnswers() {

        params.put("A1_Score", answers.get(2));
        params.put("A2_Score", answers.get(3));
        params.put("A3_Score", answers.get(4));
        params.put("A4_Score", answers.get(5));
        params.put("A5_Score", answers.get(6));
        params.put("A6_Score", answers.get(7));
        params.put("A7_Score", answers.get(8));
        params.put("A8_Score", answers.get(9));
        params.put("anxiety", answers.get(1));


        //After Button click

        ProgressDialog progressDialog = new ProgressDialog(AutismTestActivity.this);
        String path = "autismtest";

        RestClient.post(path, null, params, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                progressDialog.setCancelable(false);
                progressDialog.setMessage(getString(R.string.please_wait));
                progressDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {
                progressDialog.dismiss();
                if (response == null) {
                    Toast.makeText(AutismTestActivity.this,
                            "No response from server",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                final TextView message = new TextView(AutismTestActivity.this);
                final SpannableString s =
                        new SpannableString((response.equals("true")) ? getText(R.string.autism_positive) : (response.equals("false")) ? getText(R.string.autism_negative) : response);
                Linkify.addLinks(s, Linkify.WEB_URLS);
                message.setText(s);
                message.setPadding(32, 32, 32, 32);
                message.setMovementMethod(LinkMovementMethod.getInstance());

                AlertDialog.Builder alert = new AlertDialog.Builder(AutismTestActivity.this)
                        .setView(message)
                        .setNegativeButton(R.string.exit, (dialog, which) -> {
                            finishAffinity();
                        })
                        .setCancelable(false);

                if (response.equals("true")) {
                    alert.show();
                } else {
                    alert.setPositiveButton(R.string.cont, (dialog, which) -> {
                        Intent intent = new Intent(AutismTestActivity.this, ASDTestActivity.class);
                        intent.putExtras(details);
                        startActivity(intent);
                    }).show();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
                progressDialog.dismiss();
                if (response == null) {
                    Toast.makeText(AutismTestActivity.this,
                            "No response from server",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                final TextView message = new TextView(AutismTestActivity.this);
                final SpannableString s =
                        new SpannableString((response.equals("true")) ? getText(R.string.autism_positive) : (response.equals("false")) ? getText(R.string.autism_negative) : response);
                Linkify.addLinks(s, Linkify.WEB_URLS);
                message.setText(s);
                message.setPadding(32, 32, 32, 32);
                message.setMovementMethod(LinkMovementMethod.getInstance());

                AlertDialog.Builder alert = new AlertDialog.Builder(AutismTestActivity.this)
                        .setView(message)
                        .setNegativeButton(R.string.exit, (dialog, which) -> {
                            finishAffinity();
                        })
                        .setCancelable(false);

                if (response.equals("true")) {
                    alert.show();
                } else {
                    alert.setPositiveButton(R.string.cont, (dialog, which) -> {
                        Intent intent = new Intent(AutismTestActivity.this, ASDTestActivity.class);
                        intent.putExtras(details);
                        startActivity(intent);
                    }).show();
                }
            }

        });

//        startActivity(new Intent(AutismTestActivity.this, ConfigurationActivity.class));
    }


}
