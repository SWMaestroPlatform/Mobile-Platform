package soma.iot.sympathyhome.fragment;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import soma.iot.sympathyhome.R;
import soma.iot.sympathyhome.ui.SYMHOMEFragment;

/**
 * A fragment with a Google +1 button.
 */
public class SmartHomeItemFragment extends SYMHOMEFragment {

    public enum SMARTTHING {CONCENT, DOOR };

    public SmartHomeItemFragment() {
        // Required empty public constructor
    }

    public static SmartHomeItemFragment newInstance() {
        SmartHomeItemFragment fragment = new SmartHomeItemFragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_smart_home_item, container, false);

        //Find the +1 button
//        mPlusOneButton = (PlusOneButton) view.findViewById(R.id.plus_one_button);

        initFragment(view);
        setLayout(view);


        return view;
    }


    public boolean mToggleSwitch = false;
    private SMARTTHING mSmartthing = SMARTTHING.CONCENT;

    private TextView mToggleTextView;
    private ImageButton mImageButton;
    private ImageView mSmartthingImageview;

    @Override
    public void initFragment(View view)
    {
    }

    @Override
    public void setLayout(View view)
    {
        mToggleTextView = (TextView) view.findViewById(R.id.fragment_smart_home_item_textview);

        mImageButton = (ImageButton) view.findViewById(R.id.fragment_smart_home_item_imagebutton);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStatus();
            }
        });

        initwithToggle(mToggleSwitch);

        mSmartthingImageview = (ImageView) view.findViewById(R.id.fragment_smart_home_item_imageview);

        switch (mSmartthing)
        {
            case CONCENT:
                mSmartthingImageview.setImageResource(R.drawable.btn_smartthing1);
                break;
            case DOOR:
                mSmartthingImageview.setImageResource(R.drawable.btn_smartthing2);
                break;
            default:
                break;
        }

    }

    public void setSmartThings(SMARTTHING smartthing, boolean mToggleSwitch){
//        initwithToggle(mToggleSwitch);
        mSmartthing = smartthing;
        this.mToggleSwitch = mToggleSwitch;
    }

    private void changeStatus(){
        if (mToggleSwitch){
            mToggleSwitch = false;
            mImageButton.setImageResource(R.drawable.btn_smartthings_gray);
            mToggleTextView.setText("OFF");
        }
        else {
            mToggleSwitch = true;
            mImageButton.setImageResource(R.drawable.btn_smartthings);
            mToggleTextView.setText("ON");
        }
    }

    private void initwithToggle(boolean toggle)
    {
        if (mToggleSwitch)  //true
        {
            mImageButton.setImageResource(R.drawable.btn_smartthings);
            mToggleTextView.setText("ON");
        }
        else
        {
            mImageButton.setImageResource(R.drawable.btn_smartthings_gray);
            mToggleTextView.setText("OFF");
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        // Refresh the state of the +1 button each time the activity receives focus.
//        mPlusOneButton.initialize(PLUS_ONE_URL, PLUS_ONE_REQUEST_CODE);
    }


}
