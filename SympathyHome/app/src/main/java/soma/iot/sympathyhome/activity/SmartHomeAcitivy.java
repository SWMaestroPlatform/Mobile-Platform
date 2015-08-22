package soma.iot.sympathyhome.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import soma.iot.sympathyhome.R;
import soma.iot.sympathyhome.fragment.SmartHomeItemFragment;
import soma.iot.sympathyhome.ui.SYMHOMEActivity;

public class SmartHomeAcitivy extends SYMHOMEActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_home_acitivy);

        initActivity();
        setLayout();
    }

    private Button mTabImageButton1;
    private Button mTabImageButton2;

    private SmartHomeItemFragment mSmartHomeFragment1;
    private SmartHomeItemFragment mSmartHomeFragment2;


    @Override
    public void initActivity() {
        mTabImageButton1 = (Button) findViewById(R.id.activity_smart_home_activity_btn1);
        mTabImageButton2 = (Button) findViewById(R.id.activity_smart_home_activity_btn2);

        mSmartHomeFragment1 = SmartHomeItemFragment.newInstance();
        mSmartHomeFragment1.setSmartThings(SmartHomeItemFragment.SMARTTHING.CONCENT, false);

        mSmartHomeFragment2 = SmartHomeItemFragment.newInstance();
        mSmartHomeFragment2.setSmartThings(SmartHomeItemFragment.SMARTTHING.DOOR, false);

        getFragmentManager().beginTransaction()
                .replace(R.id.activity_smart_home_activity_fragment_container, mSmartHomeFragment1)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setLayout() {
        mTabImageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.activity_smart_home_activity_fragment_container, mSmartHomeFragment1)
                        .addToBackStack(null)
                        .commit();
            }
        });

        mTabImageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.activity_smart_home_activity_fragment_container, mSmartHomeFragment2)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_smart_home_acitivy, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
