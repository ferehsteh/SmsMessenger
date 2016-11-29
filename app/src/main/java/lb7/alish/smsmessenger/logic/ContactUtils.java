package lb7.alish.smsmessenger.logic;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import lb7.alish.smsmessenger.MyApplication;

/**
 * Created by AliSh on 11/29/2016.
 */

public class ContactUtils {

    public static String contactName(String phoneNumber) {
        String contactName = phoneNumber;
        ContentResolver cr = MyApplication.getContext().getContentResolver();
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        Cursor cursor = cr.query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);
        if (cursor != null) {
            if(cursor.moveToFirst()) {
                contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
            }
            cursor.close();
        }

        return contactName;
    }
}
