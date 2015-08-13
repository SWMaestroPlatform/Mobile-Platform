package soma.iot.sympathyhome.fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import soma.iot.sympathyhome.FloatingView.FloatingActionButton;
import soma.iot.sympathyhome.R;

/**
 * Created by seojunkyo on 15. 8. 11..
 */
public class NotificationFrament extends Fragment{

    private LayoutInflater inflater;
    private FloatingActionButton mFloatingButton;
    private ListView mListView;
    private ArrayList<String> items;

    private PopupWindow pwindo;
    private int mWidthPixels, mHeightPixels;

    private Button btn_cancel;


    public NotificationFrament() {
        // Required empty public constructor
    }

    public static NotificationFrament newInstance() {
        NotificationFrament fragment = new NotificationFrament();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reminder_notification, container, false);
        this.inflater = inflater;

        initialize(view);
        setLayout(view);

        return view;
    }

    public void initialize(View view) {
        mListView = (ListView) view.findViewById(R.id.mListView);
        mFloatingButton = (FloatingActionButton) view.findViewById(R.id.mFloatingActionButton);

        String[] item = {"apple", "banana", "Canada"};
        items = new ArrayList<>(Arrays.asList(item));

        WindowManager w = getActivity().getWindowManager();
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
        // since SDK_INT = 1;
        mWidthPixels = metrics.widthPixels;
        mHeightPixels = metrics.heightPixels;

        if (Build.VERSION.SDK_INT >= 17)
            try {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
                mWidthPixels = realSize.x;
                mHeightPixels = realSize.y;
            } catch (Exception ignored) {
            }
    }

    public void setLayout(View view) {

        mListView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items));

        mFloatingButton.attachToListView(mListView);
        mFloatingButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity().getApplicationContext(),
                //        "FloatingButton Clicked", Toast.LENGTH_SHORT).show();
                initiatePopupWindow(v);
            }
        });
        mFloatingButton.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),
                        "FloatingButton Long Clicked", Toast.LENGTH_SHORT)
                        .show();
                return true;
            }
        });
    }

    private void initiatePopupWindow(View view) {
        try {
            //  LayoutInflater 객체와 시킴
            LayoutInflater inflater = (LayoutInflater) NotificationFrament.this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View layout = inflater.inflate(R.layout.popup_reminder,
                    (ViewGroup) view.findViewById(R.id.popup_reminder));


            pwindo = new PopupWindow(layout, mWidthPixels - 100, mHeightPixels - 500, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
            btn_cancel = (Button) layout.findViewById(R.id.popup_reminder_btn_close);
            btn_cancel.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //Toast.makeText(getActivity().getApplicationContext(),
                    //        "FloatingButton Clicked", Toast.LENGTH_SHORT).show();
                    pwindo.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
