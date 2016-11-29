package lb7.alish.smsmessenger.logic;

import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.telephony.SmsManager;

import java.util.ArrayList;

import lb7.alish.smsmessenger.MyApplication;
import lb7.alish.smsmessenger.view.messagelist.MessageInfo;

/**
 * Created by AliSh on 11/29/2016.
 */

public class SmsUtils {
    public static ArrayList<MessageInfo> readAllSms() {
        Cursor cursor = null;
        cursor = MyApplication.getContext().getContentResolver().query(Uri.parse("content://sms")
                , null
                , "address IS NOT NULL) GROUP BY (thread_id"
                , null
                , null);
        ArrayList<MessageInfo> mMessages = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String messageText = cursor.getString(cursor.getColumnIndexOrThrow("body"));
                String contact = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));

                DirectionType directionType;
                if (cursor.getString(cursor.getColumnIndexOrThrow("type"))
                        .contains(Telephony.Sms.MESSAGE_TYPE_INBOX + "")) {
                    directionType = DirectionType.INPUT;
                } else {
                    directionType = DirectionType.OUTPUT;
                }
                mMessages.add(new MessageInfo(messageText.replaceAll("\\n", " "), contact, date
                        , /*ContactUtils.contactName(contact)*/"", directionType));
                // use msgData
            } while (cursor.moveToNext());
            cursor.close();
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
        if (cursor != null && cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String messageText = cursor.getString(cursor.getColumnIndexOrThrow("body"));
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));

                DirectionType directionType;
                if (cursor.getString(cursor.getColumnIndexOrThrow("type")).contains("1")) {
                    directionType = DirectionType.INPUT;
                } else {
                    directionType = DirectionType.OUTPUT;
                }
                mMessages.add(new MessageInfo(messageText, contact, date
                        , /*ContactUtils.contactName(contact)*/"", directionType));
                // use msgData
            } while (cursor.moveToNext());
            cursor.close();
        }
        return mMessages;
    }

    public static void sendMessage(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }
}
