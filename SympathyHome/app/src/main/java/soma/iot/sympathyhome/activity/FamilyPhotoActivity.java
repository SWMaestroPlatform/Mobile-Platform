package soma.iot.sympathyhome.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import soma.iot.sympathyhome.R;
import soma.iot.sympathyhome.ui.SYMHOMEActivity;
import soma.iot.sympathyhome.util.YSDataUtil;
import soma.iot.sympathyhome.util.YSTransformation;

public class FamilyPhotoActivity extends SYMHOMEActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_photo);

        initActivity();
        setLayout();
    }

    private final int REQUEST_IMAGE = 002;

    private Button mPhotoAddButton;

    private ListView mImageListView;
    private FamilyPhotoAdapter mImageAdapter;

    private Map<String, List<String>> mFamilyPhotoMap;

    @Override
    public void setLayout() {

        mPhotoAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE);
            }
        });


        mFamilyPhotoMap = YSDataUtil.getInstance().getFamilyPhotoMap();
        Iterator<String> keys = mFamilyPhotoMap.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            mImageAdapter.addSections(key, new FamilyImageViewAdapter(mFamilyPhotoMap.get(key)));
        }

        mImageListView.setAdapter(mImageAdapter);
    }


    @Override
    public void initActivity() {
        mPhotoAddButton = (Button) findViewById(R.id.familyphotoactivity_button_add);

        mImageListView = (ListView) findViewById(R.id.familyphotoactivity_listview);

        mImageAdapter = new FamilyPhotoAdapter();

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

    private String fullPath;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if(requestCode == REQUEST_IMAGE && resultCode == Activity.RESULT_OK && data != null)
//        {
//            Log.d("YoonTag", "====== OnActivityResult is start ========= \n");
//
//            Uri selPhotoUri = data.getData();
//            try {
//                Bitmap selPhoto = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selPhotoUri);
////                Bitmap roundPhoto = YSUtility.GetBitmapClippedCircle(selPhoto);
//                Picasso.with(getActivity()).load(selPhotoUri).fit().into(selectImageView);
////                selectImageView.setImageBitmap(selPhoto);
////                adapter.addImage(targetPosition, roundPhoto);
////                imageView.setImageBitmap(roundPhoto);
//                fullPath = YSUtility.getRealPathFromURI(getActivity(), selPhotoUri);
//            } catch (FileNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }

    }


    class FamilyPhotoAdapter extends BaseAdapter{

        public final Map<String,FamilyImageViewAdapter> sections = new LinkedHashMap<String,FamilyImageViewAdapter>();
        public final ArrayAdapter<String> headers;
        public final static int TYPE_SECTION_HEADER = 0;

        private LayoutInflater mInflater;

        private List<String> mItems = new ArrayList<>();
        private TreeSet<Integer> mSectionHeader = new TreeSet<Integer>();

        public FamilyPhotoAdapter() {
            this.mInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            headers = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1);

        }

        public void addSections(String sectionHeader, FamilyImageViewAdapter adapter){
            this.headers.add(sectionHeader);
            this.sections.put(sectionHeader, adapter);
            notifyDataSetChanged();
        }

        public Object getItem(int position) {
            for(Object section : this.sections.keySet()) {
                Adapter adapter = sections.get(section);
                int size = adapter.getCount() + 1;

                // check if position inside this section
                if(position == 0) return section;
                if(position < size) return adapter.getItem(position - 1);

                // otherwise jump into next section
                position -= size;
            }
            return null;
        }

        public int getCount() {
            // total together all sections, plus one for each section header
            int total = 0;
            for(Adapter adapter : this.sections.values())
                total += adapter.getCount() + 1;
            return total;
        }

        public int getViewTypeCount() {
            // assume that headers count as one, then total all sections
            int total = 1;
            for(Adapter adapter : this.sections.values())
                total += adapter.getViewTypeCount();
            return total;
        }

        public int getItemViewType(int position) {
            int type = 1;
            for(Object section : this.sections.keySet()) {
                Adapter adapter = sections.get(section);
                int size = adapter.getCount() + 1;

                // check if position inside this section
                if(position == 0) return TYPE_SECTION_HEADER;
                if(position < size) return type + adapter.getItemViewType(position - 1);

                // otherwise jump into next section
                position -= size;
                type += adapter.getViewTypeCount();
            }
            return -1;
        }

        public boolean areAllItemsSelectable() {
            return false;
        }

        public boolean isEnabled(int position) {
            return (getItemViewType(position) != TYPE_SECTION_HEADER);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            int sectionnum = 0;
            for(Object section : this.sections.keySet()) {
                Adapter adapter = sections.get(section);
                int size = adapter.getCount() + 1;

                // check if position inside this section
                if(position == 0) return headers.getView(sectionnum, convertView, parent);
                if(position < size) return adapter.getView(position - 1, convertView, parent);

                // otherwise jump into next section
                position -= size;
                sectionnum++;
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }

    class FamilyImageViewAdapter extends BaseAdapter{

        private LayoutInflater mInflater;
        private List<String> mItems = new ArrayList<>();

        public FamilyImageViewAdapter() {
            this.mInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public FamilyImageViewAdapter(List<String> Items) {
            this.mInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mItems = Items;
        }

        public void addItem(final String item) {
            mItems.add(item);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return (mItems.size()-1)/3 + 1;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        private static final int MAX_WIDTH = 1024;
        private static final int MAX_HEIGHT = 768;

        int size = (int) Math.ceil(Math.sqrt(MAX_WIDTH * MAX_HEIGHT));


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            int rowType = getItemViewType(position);

            if(convertView == null) {
                holder = new ViewHolder();

                convertView = mInflater.inflate(R.layout.activity_family_photo_item, null);

                holder.mImageView1 = (ImageView) convertView.findViewById(R.id.activity_family_photo_item_imageview1);
                holder.mImageView2 = (ImageView) convertView.findViewById(R.id.activity_family_photo_item_imageview2);
                holder.mImageView3 = (ImageView) convertView.findViewById(R.id.activity_family_photo_item_imageview3);


                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (position < mItems.size()/3)
            {
//                YSTransformation transformation = new YSTransformation(holder.mImageView1.getWidth());
                // Loads given image

                Picasso.with(getBaseContext()).load(mItems.get(position * 3)).resize(300, 300).centerCrop().into(holder.mImageView1);
                Picasso.with(getBaseContext()).load(mItems.get(position * 3 + 1)).resize(300, 300).centerCrop().into(holder.mImageView2);
                Picasso.with(getBaseContext()).load(mItems.get(position*3+2)).resize(300,300).centerCrop().into(holder.mImageView3);
            }
            else {
                switch (mItems.size()%3)
                {
                    case 2:
                        Picasso.with(getBaseContext()).load(mItems.get(position*3 +1)).resize(300,300).centerCrop().into(holder.mImageView2);
                    case 1:
                        Picasso.with(getBaseContext()).load(mItems.get(position*3)).resize(300,300).centerCrop().into(holder.mImageView1);
//                        Picasso.with(getBaseContext()).load(mItems.get(position*3)).fit().into(holder.mImageView1);
                        break;
                }
            }

            return convertView;
        }
    }

    private static class ViewHolder {
        ImageView mImageView1;
        ImageView mImageView2;
        ImageView mImageView3;
    }


}
