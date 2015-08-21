package soma.iot.sympathyhome.util;

import android.graphics.Bitmap;


import com.squareup.picasso.Transformation;

/**
 * Created by YoonSeok on 15. 8. 22..
 */
public class YSTransformation implements Transformation {

    public YSTransformation(int width) {
        mWidth = width;
    }

    public static YSTransformation getInstance(int width){
        return new YSTransformation(width);
    }

    public int mWidth;

    @Override
    public Bitmap transform(final Bitmap source) {
        int targetWidth = mWidth;

        double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
        int targetHeight = (int) (targetWidth * aspectRatio);
        Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
        if (result != source) {
            // Same bitmap is returned if sizes are the same
            source.recycle();
        }
        return result;
    }

    @Override
    public String key() {
        return "rounded(radius=)";
    }
}
