package soma.iot.sympathyhome.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soma.iot.sympathyhome.R;

/**
 * Created by seojunkyo on 15. 8. 11..
 */
public class LetterFrament extends Fragment{

    private LayoutInflater inflater;

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

    public void setLayout(LayoutInflater inflater) {

    }

    public void initialize(View view) {

    }
}
