package soma.iot.sympathyhome.activity;

import android.animation.BidirectionalTypeConverter;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import soma.iot.sympathyhome.R;
import soma.iot.sympathyhome.ui.SYMHOMEActivity;

public class FamilyPhotoActivity extends SYMHOMEActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_photo);

        initActivity();
        setLayout();
    }

    private ImageView mImageAddTest;

    private ListView mImageListView;
    private FamilyPhotoAdapter mImageAdapter;


    @Override
    public void setLayout() {

    }

    @Override
    public void initActivity() {
        mImageAddTest = (ImageView) findViewById(R.id.familyphotoactivity_imageview_add);

        mImageListView = (ListView) findViewById(R.id.familyphotoactivity_listview);

        mImageAdapter = new FamilyPhotoAdapter();
        mImageListView.setAdapter(mImageAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_family_photo, menu);
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

    class FamilyPhotoAdapter extends BaseAdapter{

        private LayoutInflater mInflater;
        private List<String> mItems = new ArrayList<>();

        public FamilyPhotoAdapter() {
            this.mInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if(convertView == null) {
                convertView = mInflater.inflate(R.layout.activity_family_photo_item, null);

                holder = new ViewHolder();

                holder.mImageView1 = (ImageView) convertView.findViewById(R.id.activity_family_photo_item_imageview1);
                holder.mImageView2 = (ImageView) convertView.findViewById(R.id.activity_family_photo_item_imageview2);
                holder.mImageView3 = (ImageView) convertView.findViewById(R.id.activity_family_photo_item_imageview3);

                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            Picasso.with(getBaseContext()).load("http://i.imgur.com/DvpvklR.png").fit().into(holder.mImageView1);
            Picasso.with(getBaseContext()).load("http://i.imgur.com/DvpvklR.png").fit().into(holder.mImageView2);
            Picasso.with(getBaseContext()).load("http://i.imgur.com/DvpvklR.png").fit().into(holder.mImageView3);

            return convertView;
        }
    }

    private static class ViewHolder {
        ImageView mImageView1;
        ImageView mImageView2;
        ImageView mImageView3;
    }


}
