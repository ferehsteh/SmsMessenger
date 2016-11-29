package lb7.alish.smsmessenger.logic;

import android.database.Cursor;
import android.net.Uri;
import android.telephony.SmsManager;

import java.util.ArrayList;

import lb7.alish.smsmessenger.MyApplication;
import lb7.alish.smsmessenger.view.messagelist.MessageInfo;

/**
 * Created by AliSh on 11/29/2016.
 */

public class SmsUtils {
    public static ArrayList<MessageInfo> readAllSms() {
        Cursor cursor = MyApplication.getContext().getContentResolver().query(Uri.parse("content://sms/")
                , null
                , "address IS NOT NULL) GROUP BY (address"
                , null
                , null);
        ArrayList<MessageInfo> mMessages = new ArrayList<>();
        System.out.println("readSms start ");
        if (cursor != null && cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String messageText = cursor.getString(cursor.getColumnIndexOrThrow("body"));
                System.out.println("readSms messageText : " + messageText);
                String contact = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                System.out.println("readSms contact : " + contact);
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                System.out.println("readSms date : " + date);

                DirectionType directionType;
                if (cursor.getString(cursor.getColumnIndexOrThrow("type")).contains("1")) {
                    directionType = DirectionType.INPUT;
                } else {
                    directionType = DirectionType.OUTPUT;
                }
                mMessages.add(new MessageInfo(messageText, contact, date, directionType));
                // use msgData
            } while (cursor.moveToNext());
        }
        return mMessages;
    }

    public static ArrayList<MessageInfo> readSmsByContact(String contact) {
        Cursor cursor = MyApplication.getContext().getContentResolver().query(Uri.parse("content://sms/")
                , null
                , "address = '" + contact + "'"
                , null
                , null);
        ArrayList<MessageInfo> mMessages = new ArrayList<>();
        System.out.println("readSms start ");
        if (cursor != null && cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String messageText = cursor.getString(cursor.getColumnIndexOrThrow("body"));
                System.out.println("readSms messageText : " + messageText);
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                System.out.println("readSms date : " + date);

                DirectionType directionType;
                if (cursor.getString(cursor.getColumnIndexOrThrow("type")).contains("1")) {
                    directionType = DirectionType.INPUT;
                } else {
                    directionType = DirectionType.OUTPUT;
                }
                mMessages.add(new MessageInfo(messageText, contact, date, directionType));
                // use msgData
            } while (cursor.moveToNext());
        }
        return mMessages;
    }

    public static void sendMessage(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }
}
