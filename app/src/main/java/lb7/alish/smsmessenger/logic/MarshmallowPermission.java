package lb7.alish.smsmessenger.logic;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import lb7.alish.smsmessenger.MyApplication;

/**
 * Created by AliSh on 12/1/2016.
 */

public class MarshmallowPermission {

    public static boolean isPermissionGuaranted(String[] permissionArray) {
        for (String permission : permissionArray) {
            if (ActivityCompat.checkSelfPermission(MyApplication.getContext(), permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}
