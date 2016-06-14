package com.sotan.mircea.shower;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.WindowManager;

/**
 * @author mirceasotan
 */
public class DisplayUtils {

    /**
     * @param c Context for obtaining the window service
     * @return Width in pixels
     */
    public static int getDisplayWidth(@Nullable Context c) {
        if (c == null) {
            return 0;
        }

        WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size.x;
    }
}
