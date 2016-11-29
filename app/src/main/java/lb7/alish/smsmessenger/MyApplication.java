package lb7.alish.smsmessenger;

import android.app.Application;
import android.content.Context;

/**
 * Created by AliSh on 11/29/2016.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;

    public static Context getContext() {
        return mInstance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
