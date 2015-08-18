package soma.iot.sympathyhome.fragment;

import android.app.DatePickerDialog;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import soma.iot.sympathyhome.R;
import soma.iot.sympathyhome.util.FloatingActionButton;
import soma.iot.sympathyhome.util.ReminderDate;
import soma.iot.sympathyhome.util.ReminderDateAdapter;

/**
 * Created by seojunkyo on 15. 8. 11..
 */
public class NotificationFrament extends Fragment {

    private LayoutInflater inflater;
    private FloatingActionButton mFloatingButton;
    public ArrayList<ReminderDate> items;
    private ReminderDateAdapter adapter;

    private PopupWindow pwindo;
    private int mWidthPixels, mHeightPixels;
    private int mYear, mMonth, mDay;

    public NotificationFrament() {
        // Required empty public constructor
    }

    public static NotificationFrament newInstance() {
        NotificationFrament fragment = new NotificationFrament();

        return fragment;
    }

    private ListView mListView;
    private Button btn_ok;
    private Button btn_cancel;
    private Button reminder_btn_date;
    public TextView curDate;
    public EditText reminder_contents;

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
        items = new ArrayList<ReminderDate>();

        WindowManager w = getActivity().getWindowManager();
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);

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

    public void initiatePopupWindow(View view) {
        try {
            //  LayoutInflater 객체와 시킴
            LayoutInflater inflater = (LayoutInflater) NotificationFrament.this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View layout = inflater.inflate(R.layout.popup_reminder,
                    (ViewGroup) view.findViewById(R.id.popup_reminder));

            pwindo = new PopupWindow(layout, mWidthPixels - 140, mHeightPixels - 1000, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

            curDate = (TextView) layout.findViewById(R.id.popup_reminder_date);
            reminder_contents = (EditText) layout.findViewById(R.id.popup_reminder_contents);

            Calendar cal= new GregorianCalendar();
            mYear = cal.get(Calendar.YEAR);
            mMonth = cal.get(Calendar.MONTH);
            mDay = cal.get(Calendar.DAY_OF_MONTH);

            UpdateNow();

            btn_cancel = (Button) layout.findViewById(R.id.popup_reminder_btn_close);
            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pwindo.dismiss();
                }
            });

            reminder_btn_date = (Button) layout.findViewById(R.id.popup_reminder_btn_date);
            reminder_btn_date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DatePickerDialog(getActivity(),mDateSetListener, mYear, mMonth, mDay).show();
                }
            });

            btn_ok = (Button) layout.findViewById(R.id.popup_reminder_btn_check);
            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        items.add(new ReminderDate(curDate.getText().toString(), reminder_contents.getText().toString()));
                    } catch (Exception e) {
                        items.add(new ReminderDate("5월 18일", "빨래널기"));
                    }
                    adapter = new ReminderDateAdapter(getActivity().getLayoutInflater(), items);
                    mListView.setAdapter(adapter);
                    pwindo.dismiss();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    UpdateNow();
                }
            };



    public void UpdateNow() {
        curDate.setText(String.format("%d/%d/%d", mYear, mMonth + 1, mDay));
    }
}