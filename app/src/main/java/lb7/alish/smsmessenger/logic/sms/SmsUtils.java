package lb7.alish.smsmessenger.logic.sms;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.util.ArrayList;

import lb7.alish.smsmessenger.MyApplication;
import lb7.alish.smsmessenger.logic.DirectionType;
import lb7.alish.smsmessenger.view.messagelist.MessageInfo;

/**
 * Created by AliSh on 11/29/2016.
 */

public class SmsUtils {

    public static ArrayList<MessageInfo> readAllSms() {
        Cursor cursor = MyApplication.getContext().getContentResolver().query(Uri.parse("content://sms")
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

    public static void sendMessage(String phoneNumber, String message, int simId) {
        if (simId == 1) {
            SmsManager sms = SmsManager.getDefault();

            String DELIVERED = "SMS_DELIVERED";
            BroadcastReceiver deliveryBroadcastReceiver = new DeliverReceiver();
            PendingIntent deliveredPI = PendingIntent.getBroadcast(MyApplication.getContext(), 0, new Intent(DELIVERED), 0);
            MyApplication.getContext().registerReceiver(deliveryBroadcastReceiver, new IntentFilter(DELIVERED));

            sms.sendTextMessage(phoneNumber, null, message, null, deliveredPI);
        } else {
            Toast.makeText(MyApplication.getContext(), "Dual Sim is not supported yet", Toast.LENGTH_SHORT).show();
        }
    }


}
