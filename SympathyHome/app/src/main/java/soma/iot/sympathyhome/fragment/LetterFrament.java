package soma.iot.sympathyhome.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

import soma.iot.sympathyhome.R;

/**
 * Created by seojunkyo on 15. 8. 11..
 */
public class LetterFrament extends Fragment{

    private LayoutInflater inflater;
    private Button mBtnRecorde;
    public Chronometer chronometer;

    private boolean flagTimer;

    public LetterFrament() {
        // Required empty public constructor
    }

    public static LetterFrament newInstance() {
        LetterFrament fragment = new LetterFrament();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reminder_letter, container, false);
        this.inflater = inflater;
        initialize(view);

        setLayout(inflater);
        return view;
    }

    public void initialize(View view) {
        flagTimer = false;

        chronometer = (Chronometer) view.findViewById(R.id.fragment_letter_timer);
        mBtnRecorde = (Button) view.findViewById(R.id.fragment_letter_recorde);
    }

    public void setLayout(LayoutInflater inflater) {
        mBtnRecorde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flagTimer == false) {
                    chronometer.start();
                    flagTimer = true;
                }
                else {
                    chronometer.stop();
                    flagTimer = false;
                }
            }
        });
    }
}
