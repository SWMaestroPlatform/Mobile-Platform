package soma.iot.sympathyhome.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by YoonSeok on 15. 8. 10..
 */
public abstract class SYMHOMEActivity extends FragmentActivity{

//    private View decorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();

//        decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    public abstract void setLayout();

    public abstract void initActivity();
}
