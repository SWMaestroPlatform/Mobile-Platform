package soma.iot.sympathyhome.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YoonSeok on 15. 8. 22..
 */
public class YSDataUtil {

    public static YSDataUtil getInstance()
    {
        return new YSDataUtil();
    }

    public static String getResourceString(String str)
    {
        return "android.resource://soma.iot.sympathyhome/drawable/"+str;
    }

    Map<String, List<String>> mHashMap;
    public Map<String, List<String>> getFamilyPhotoMap()
    {
        mHashMap = new HashMap<>();

        List<String> list = new ArrayList<>();
        list.add(YSDataUtil.getResourceString("photo_autumn_02"));
        list.add(YSDataUtil.getResourceString("photo_autumn_01"));
        list.add(YSDataUtil.getResourceString("photo_autumn_03"));
        list.add(YSDataUtil.getResourceString("photo_autumn_04"));
        list.add(YSDataUtil.getResourceString("photo_autumn_05"));
        list.add(YSDataUtil.getResourceString("photo_autumn_06"));
        list.add(YSDataUtil.getResourceString("photo_autumn_07"));
        list.add(YSDataUtil.getResourceString("photo_autumn_08"));
        list.add(YSDataUtil.getResourceString("photo_autumn_09"));
        list.add(YSDataUtil.getResourceString("photo_autumn_10"));
        list.add(YSDataUtil.getResourceString("photo_autumn_11"));
        list.add(YSDataUtil.getResourceString("photo_autumn_12"));
        list.add(YSDataUtil.getResourceString("photo_autumn_13"));

        mHashMap.put("2010년 10월 12일", list);

        List<String> list2 = new ArrayList<>();
        list2.add(YSDataUtil.getResourceString("photo_birthday_01"));
        list2.add(YSDataUtil.getResourceString("photo_birthday_02"));
        list2.add(YSDataUtil.getResourceString("photo_birthday_03"));
        list2.add(YSDataUtil.getResourceString("photo_birthday_04"));
        list2.add(YSDataUtil.getResourceString("photo_birthday_05"));
        list2.add(YSDataUtil.getResourceString("photo_birthday_06"));

        mHashMap.put("2010년 11월 1일", list2);


        List<String> list3 = new ArrayList<>();
        list3.add(YSDataUtil.getResourceString("photo_necktie_01"));
        list3.add(YSDataUtil.getResourceString("photo_necktie_02"));
        list3.add(YSDataUtil.getResourceString("photo_necktie_03"));

        mHashMap.put("2013년 2월 20일", list3);

        return mHashMap;
    }
}
