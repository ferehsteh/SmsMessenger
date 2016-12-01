package lb7.alish.smsmessenger.logic;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lb7.alish.smsmessenger.MyApplication;

import static android.content.ContentValues.TAG;

/**
 * Created by AliSh on 12/1/2016.
 */

public class SimCardUtils {

    public static boolean isDualSim() {
        try {
            TelephonyManager secondSimInfo = getSecondSimInfo(MyApplication.getContext());
            return secondSimInfo != null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (NoSuchMethodException e) {
            return false;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static TelephonyManager getSecondSimInfo(Context context) throws ClassNotFoundException, NoSuchMethodException
            , InvocationTargetException, IllegalAccessException {
        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        Class<?> telephonyClass = Class.forName(telephony.getClass().getName());
        Class<?>[] parameter = new Class[1];
        parameter[0] = int.class;

        Method getFirstMethod = telephonyClass.getMethod("getDefault", parameter);

        Object[] obParameter = new Object[1];
        obParameter[0] = 1;
        TelephonyManager second = (TelephonyManager) getFirstMethod.invoke(null, obParameter);

        Log.d(TAG, "Device status: " + second.getSimState()
                + ", operator: " + second.getNetworkOperator()
                + "/" + second.getNetworkOperatorName());

        return second;
    }

}
