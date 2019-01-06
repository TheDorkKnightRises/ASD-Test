package thedorkknightrises.asdtest.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import thedorkknightrises.asdtest.R;


public class CardAdapter extends ArrayAdapter<String> {

    String questions[] = {getContext().getString(R.string.q_anxiety),getContext().getString(R.string.q_hand_finger_mannerism),getContext().getString(R.string.q_imagination),getContext().getString(R.string.q_echolalia),getContext().getString(R.string.q_social_overtures),getContext().getString(R.string.q_self_injurious_behavior),getContext().getString(R.string.q_shared_enjoyment),getContext().getString(R.string.q_tantrums),getContext().getString(R.string.q_eye_contact)};

    public CardAdapter( @NonNull Context context, int resource) {
        super(context, resource);
    }


    @NonNull
    @Override
    public View getView(int position,  @Nullable View convertView,  @NonNull ViewGroup parent) {
        TextView ques_no=(TextView)convertView.findViewById(R.id.ques_num);
        ques_no.setText(getItem(position));
        TextView question=(TextView)convertView.findViewById(R.id.question);
        question.setText(questions[position]);

        if(position==0){
            convertView.findViewById(R.id.seekbar).setVisibility(View.VISIBLE);
            convertView.findViewById(R.id.radio_group).setVisibility(View.GONE);
        }
        else{
            convertView.findViewById(R.id.seekbar).setVisibility(View.GONE);
            convertView.findViewById(R.id.radio_group).setVisibility(View.VISIBLE);
        }

        return convertView;
    }
}
