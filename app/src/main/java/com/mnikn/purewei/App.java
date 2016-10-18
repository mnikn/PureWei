package com.mnikn.purewei;

import android.app.Application;
import android.content.Context;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class App extends Application {

    private static boolean sIsNightMode = false;

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getAppContext(){
        return sContext;
    }

    public static void nightModeChange(){
        sIsNightMode = !sIsNightMode;
    }

    public static boolean isNightMode(){
        return sIsNightMode;
    }
}
