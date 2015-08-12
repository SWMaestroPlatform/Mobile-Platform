package soma.iot.sympathyhome.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import soma.iot.sympathyhome.R;
import soma.iot.sympathyhome.fragment.LetterFrament;
import soma.iot.sympathyhome.fragment.NotificationFrament;
import soma.iot.sympathyhome.ui.SYMHOMEActivity;

public class ReminderActivity extends SYMHOMEActivity implements View.OnClickListener {

    final String TAG = "MainActivity";

    int mCurrentFragmentIndex;
    public final static int FRAGMENT_ONE = 0;
    public final static int FRAGMENT_TWO = 1;

    private Button mImgBtnNotification;
    private Button mImgBtnLetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        initActivity();
        setLayout();
    }

    @Override
    public void initActivity() {
        mImgBtnNotification = (Button)findViewById(R.id.activity_reminder_btn_notification);
        mImgBtnLetter = (Button)findViewById(R.id.activity_reminder_btn_letter);

        mCurrentFragmentIndex = FRAGMENT_ONE;

        fragmentReplace(mCurrentFragmentIndex);
    }

    @Override
    public void setLayout() {
        mImgBtnNotification.setOnClickListener(this);
        mImgBtnLetter.setOnClickListener(this);
    }

    public void fragmentReplace(int reqNewFragmentIndex) {

        Fragment newFragment = null;
        newFragment = getFragment(reqNewFragmentIndex);

        // replace fragment
        final FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Log.d(TAG, "fragmentReplace " + reqNewFragmentIndex);
        transaction.replace(R.id.fragment_reminder, newFragment);

        // Commit the transaction
        transaction.commit();
    }

    private Fragment getFragment(int idx) {
        Fragment newFragment = null;

        switch (idx) {
            case FRAGMENT_ONE:
                newFragment = new NotificationFrament();
                break;
            case FRAGMENT_TWO:
                newFragment = new LetterFrament();
                break;
            default:
                break;
        }
        return newFragment;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_reminder_btn_notification:
                mCurrentFragmentIndex = FRAGMENT_ONE;
                fragmentReplace(mCurrentFragmentIndex);
                break;
            case R.id.activity_reminder_btn_letter:
                mCurrentFragmentIndex = FRAGMENT_TWO;
                fragmentReplace(mCurrentFragmentIndex);
                break;
        }
    }
}