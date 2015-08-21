package soma.iot.sympathyhome.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soma.iot.sympathyhome.R;
import soma.iot.sympathyhome.ui.SYMHOMEFragment;

/**
 * A fragment with a Google +1 button.
 */
public class SmartHomeItemFragment extends Fragment {

    private static final int PLUS_ONE_REQUEST_CODE = 0;


    public SmartHomeItemFragment() {
        // Required empty public constructor
    }

    public void setLayout(View view)
    {

    }

    public void initFragment()
    {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_smart_home_item, container, false);

        //Find the +1 button
//        mPlusOneButton = (PlusOneButton) view.findViewById(R.id.plus_one_button);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        // Refresh the state of the +1 button each time the activity receives focus.
//        mPlusOneButton.initialize(PLUS_ONE_URL, PLUS_ONE_REQUEST_CODE);
    }


}
