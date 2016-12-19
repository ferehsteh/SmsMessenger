package lb7.alish.smsmessenger.logic.contacts;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

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
                //1
                String photoUri = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
                //2
//                InputStream inputStream = null;
//                try {
//                    if (photoUri != null) {
//                        inputStream = cr.openInputStream(Uri.parse(photoUri));
//                    }
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
                //3
//                InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(cr,
//                        ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,
//                                new Long(phones.getLong(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)))));
                contactInfos.add(new ContactInfo(name, phoneNumber, photoUri));
            } while (phones.moveToNext());
            phones.close();
        }

        return contactInfos;
    }

    public static ContactInfo getContact(String phoneNumber) {
        String contactName = phoneNumber;
        ContactInfo contactInfo = new ContactInfo(phoneNumber, phoneNumber, null);
        ContentResolver cr = MyApplication.getContext().getContentResolver();
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        Cursor cursor = cr.query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME
                , ContactsContract.PhoneLookup.PHOTO_URI}, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
                String photoUri = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.PHOTO_URI));
                contactInfo = new ContactInfo(contactName, phoneNumber, photoUri);

            }
            cursor.close();
        }

        return contactInfo;
    }

    public static String getContactPhotoURI(Context context, String phoneNumber) {
        String image = null;
        Cursor phonesCursor = null;
        if (phoneNumber != null && !phoneNumber.equals("")) {
            try {
                Uri phoneUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
                phonesCursor = context.getContentResolver().query(phoneUri, new String[]{ContactsContract.PhoneLookup.PHOTO_THUMBNAIL_URI}, null, null, null);
                if (phonesCursor != null && phonesCursor.moveToFirst()) {
                    image = phonesCursor.getString(0);
                } else {
//                Log.e("no contact ", "============");
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (phonesCursor != null && !phonesCursor.isClosed())
                        phonesCursor.close();

                } catch (Exception ex) {
                }
            }
        }
        return image;
    }

    public static InputStream getPhotoURI1(Context context, String phoneNumber) {
        String name = contactName(phoneNumber);
        String contactId = "0";
        if (!name.equals(phoneNumber)) {
            contactId = String.valueOf(getContactIDFromNumber(phoneNumber, context));
        }

        InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(),
                ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(contactId)));

        return inputStream;
    }

    public static Bitmap retrieveContactPhoto(Context context, InputStream inputStream) {

        Bitmap photo = BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.contact_pic);

        if (inputStream != null) {
            photo = BitmapFactory.decodeStream(inputStream);
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
