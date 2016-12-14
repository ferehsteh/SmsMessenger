package lb7.alish.smsmessenger;

import android.app.Application;
import android.content.Context;

/**
 * Created by AliSh on 11/29/2016.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;
    public boolean isRunFragmentBase = true;

    public static Context getContext() {
        return mInstance.getApplicationContext();
    }



    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
