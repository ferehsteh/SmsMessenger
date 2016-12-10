package lb7.alish.smsmessenger.logic.contacts;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;

import lb7.alish.smsmessenger.MyApplication;
import lb7.alish.smsmessenger.view.contacts.ContactInfo;

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
            if (cursor.moveToFirst()) {
                contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
            }
            cursor.close();
        }

        return contactName;
    }

    public static ArrayList<ContactInfo> getAllContacts() {
        ContentResolver cr = MyApplication.getContext().getContentResolver();
        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null
                , ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        ArrayList<ContactInfo> contactInfos = new ArrayList<>();
        if (phones != null && phones.moveToFirst()) {
            do {
                String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactInfos.add(new ContactInfo(name, phoneNumber));
            } while (phones.moveToNext());
            phones.close();
        }

        return contactInfos;
    }
}
