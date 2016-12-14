package lb7.alish.smsmessenger.logic.contacts;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import lb7.alish.smsmessenger.MyApplication;
import lb7.alish.smsmessenger.R;
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
                Bitmap bit_thumb = retrieveContactPhoto(MyApplication.getContext(), phoneNumber);
                contactInfos.add(new ContactInfo(name, phoneNumber, bit_thumb));
            } while (phones.moveToNext());
            phones.close();
        }

        return contactInfos;
    }

    public static Bitmap retrieveContactPhoto(Context context, String phoneNumber) {
        String name = contactName(phoneNumber);
        String contactId = "0";
        if (!name.equals(phoneNumber)) {
            contactId = String.valueOf(getContactIDFromNumber(phoneNumber, context));
        }

        Bitmap photo = BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.contact_pic);

        try {
            InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(),
                    ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(contactId)));

            if (inputStream != null) {
                photo = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return photo;
    }

    public static int getContactIDFromNumber(String phoneNumber, Context context) {
        phoneNumber = Uri.encode(phoneNumber);
        int phoneContactID = new Random().nextInt();
        Cursor contactLookupCursor = context.getContentResolver().query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, phoneNumber), new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID}, null, null, null);
        while (contactLookupCursor.moveToNext()) {
            phoneContactID = contactLookupCursor.getInt(contactLookupCursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup._ID));
        }
        contactLookupCursor.close();

        return phoneContactID;
    }
}
