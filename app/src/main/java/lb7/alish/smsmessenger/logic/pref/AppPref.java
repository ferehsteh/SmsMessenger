package lb7.alish.smsmessenger.logic.pref;

import android.content.Context;
import android.content.SharedPreferences;

import lb7.alish.smsmessenger.MyApplication;

/**
 * Created by AliSh on 12/1/2016.
 */

public class AppPref {

    private static final String KEY_MAIN_PREF = "lb7.alish.smsmessenger.logic.pref.AppPref.KEY_MAIN_PREF";
    private static final String KEY_SELECTED_SIM_PREF = "lb7.alish.smsmessenger.logic.pref.AppPref.KEY_SELECTED_SIM_PREF";
    private static final String KEY_UPDATED_TIME_SMS_DATABASE = "lb7.alish.smsmessenger.logic.pref.AppPref.KEY_UPDATED_TIME_SMS_DATABASE";
    private static AppPref mInstance;
    private SharedPreferences mSharedPreferences;

    private AppPref() {
        mSharedPreferences = MyApplication.getContext().getSharedPreferences(KEY_MAIN_PREF,
                Context.MODE_PRIVATE);
    }

    public static AppPref getInstance() {
        if (mInstance == null) {
            mInstance = new AppPref();
        }
        return mInstance;
    }

    public int getSelectedSim() {
        return mSharedPreferences.getInt(KEY_SELECTED_SIM_PREF, 1);
    }

    public void setSelectedSim(int simNumber) {
        mSharedPreferences.edit().putInt(KEY_SELECTED_SIM_PREF, simNumber).apply();
    }

    public void setUpdatedTime(long dateTime) {

        mSharedPreferences.edit().putLong(KEY_UPDATED_TIME_SMS_DATABASE, dateTime).apply();
    }

    public long getUpdatedTim() {

        return mSharedPreferences.getLong(KEY_UPDATED_TIME_SMS_DATABASE, 0L);

    }
}
