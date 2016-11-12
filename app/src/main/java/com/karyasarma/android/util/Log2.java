package com.karyasarma.android.util;

import android.util.Log;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class Log2
{
    private static boolean isRunningOnAndroid;

    static
    {
        isRunningOnAndroid = android.os.Build.VERSION.RELEASE!=null;
    }

    private Log2()
    {
    }

    public static int i(String tag, String msg)
    {
        int result = -1;

        if(isRunningOnAndroid)
        {
            result = Log.i(tag, msg);
        }
        else
        {
            System.out.println(tag+" : "+msg);
        }

        return result;
    }
}
