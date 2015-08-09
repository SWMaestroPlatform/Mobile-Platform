package soma.iot.sympathyhome.activity;

import android.content.Intent;
import android.media.Image;
import android.sax.StartElementListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import soma.iot.sympathyhome.R;
import soma.iot.sympathyhome.ui.SYMHOMEActivity;

public class MainActivity extends SYMHOMEActivity {
    private static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAcitivy();
        setLayout();

    }

    private ImageButton mImgBtnFamilyPhoto;
    private ImageButton mImgBtnSmartHome;
    private ImageButton mImgBtnMainPhoto;

    @Override
    public void initAcitivy(){
        mImgBtnFamilyPhoto = (ImageButton) findViewById(R.id.mainactivity_imagebutton_familyphoto);
        mImgBtnSmartHome = (ImageButton) findViewById(R.id.mainactivity_imagebutton_smarthome);
        mImgBtnMainPhoto = (ImageButton) findViewById(R.id.mainactivity_imagebutton_photo);
    }

    @Override
    public void setLayout(){
        mImgBtnFamilyPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FamilyPhotoActivity.class));
            }
        });

        mImgBtnSmartHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SmartHomeAcitivy.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
