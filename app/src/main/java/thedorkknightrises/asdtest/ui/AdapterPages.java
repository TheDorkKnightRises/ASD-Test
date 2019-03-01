package thedorkknightrises.asdtest.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import thedorkknightrises.asdtest.R;


public class AdapterPages extends RecyclerView.Adapter<AdapterPages.ViewHolder> {

    Context c;
    ArrayAdapter<String> strings;


    public AdapterPages(Context c, ArrayAdapter<String> strings) {
        this.c = c;
        this.strings = strings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(c).inflate(R.layout.card_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String questions[] = {c.getString(R.string.q_anxiety),c.getString(R.string.q_hand_finger_mannerism),c.getString(R.string.q_imagination),c.getString(R.string.q_echolalia),c.getString(R.string.q_social_overtures),c.getString(R.string.q_self_injurious_behavior),c.getString(R.string.q_shared_enjoyment),c.getString(R.string.q_tantrums),c.getString(R.string.q_eye_contact)};
        viewHolder.qnNo.setText(strings.getItem(i));
        viewHolder.question.setText(questions[Integer.parseInt(strings.getItem(i).charAt(9)+"")-1]);

        if(viewHolder.qnNo.getText().charAt(9)=='1'){
            viewHolder.seekLayout.setVisibility(View.VISIBLE);
            viewHolder.radioGroup.setVisibility(View.GONE);
            AutismTestActivity.pageFlag=true;

            viewHolder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    viewHolder.seekValue.setText(String.valueOf(i + 1));
                    AutismTestActivity.answers.put(AutismTestActivity.currentCard,i+1);
                    AutismTestActivity.pageFlag=true;

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
        }
        else{
            viewHolder.seekLayout.setVisibility(View.GONE);
            viewHolder.radioGroup.clearCheck();
            viewHolder.radioGroup.setVisibility(View.VISIBLE);

            viewHolder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if(viewHolder.radioGroup.getCheckedRadioButtonId()!=-1) {
                        AutismTestActivity.pageFlag = true;
                        RadioButton selectedItem=(RadioButton)radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                        int answer=selectedItem.getText().toString().equals("Agree") ? 2 : selectedItem.getText().toString().equals("Neutral") ? 1 : 0;
                        AutismTestActivity.answers.put(AutismTestActivity.currentCard, answer);
                    }
                }
            });

            if(viewHolder.radioGroup.getCheckedRadioButtonId()==-1)
                AutismTestActivity.pageFlag=false;

        }
    }

    @Override
    public int getItemCount() {
        return strings.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView qnNo,question,seekValue;
        LinearLayout seekLayout;
        SeekBar seekBar;
        RadioGroup radioGroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            qnNo = (TextView)itemView.findViewById(R.id.ques_num);
            question = (TextView)itemView.findViewById(R.id.question);
            seekLayout = (LinearLayout) itemView.findViewById(R.id.seek);
            radioGroup = (RadioGroup) itemView.findViewById(R.id.radio_group);
            seekBar = (SeekBar) itemView.findViewById(R.id.seekbar);
            seekValue = (TextView) itemView.findViewById(R.id.anxiety_value);


        }
    }
}
