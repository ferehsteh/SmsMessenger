package lb7.alish.smsmessenger.logic.sms;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import lb7.alish.smsmessenger.MyApplication;
import lb7.alish.smsmessenger.logic.pref.AppPref;
import lb7.alish.smsmessenger.sample.provider.message.DirectionType;
import lb7.alish.smsmessenger.sample.provider.message.MessageColumns;
import lb7.alish.smsmessenger.sample.provider.message.MessageContentValues;
import lb7.alish.smsmessenger.sample.provider.message.MessageCursor;
import lb7.alish.smsmessenger.sample.provider.message.MessageSelection;
import lb7.alish.smsmessenger.view.messagelist.MainActivity;
import lb7.alish.smsmessenger.view.messagelist.MessageInfo;

/**
 * Created by AliSh on 11/29/2016.
 */

public class SmsUtils {

    public static ArrayList<MessageInfo> readAllSms() {
        ArrayList<MessageInfo> mMessages = new ArrayList<>();
        MessageSelection where = new MessageSelection();
        where.orderByDate(true).groupBy(MessageColumns.THREAD_ID);
        MessageCursor cursor = where.query(MyApplication.getContext());
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String messageText = cursor.getMessageText();
                String contact = cursor.getNumber();
                Long date = cursor.getDate();
                long cid = cursor.getId();
                DirectionType directionType = cursor.getType();
                mMessages.add(new MessageInfo(messageText.replaceAll("\\n", " "), contact, date.toString(), "", directionType, cid));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return mMessages;
    }

    public static ArrayList<MessageInfo> readSmsByContact(String contact) {
        ArrayList<MessageInfo> mMessages = new ArrayList<>();
        MessageSelection where = new MessageSelection();
        where.number(contact);
        MessageCursor cursor = where.query(MyApplication.getContext());
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String messageText = cursor.getMessageText();
                Long date = cursor.getDate();
                long cid = cursor.getId();
                DirectionType directionType = cursor.getType();
                mMessages.add(new MessageInfo(messageText.replaceAll("\\n", " "), contact, date.toString(), "", directionType, cid));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return mMessages;
    }

    public static ArrayList<MessageInfo> readAllSms1() {
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
                long cid = cursor.getLong(cursor.getColumnIndex("_id"));
                //1 phone number
                //2
//                String photoURI = ContactUtils.getContactPhotoURI(MyApplication.getContext(), contact);
                DirectionType directionType;
                if (cursor.getString(cursor.getColumnIndexOrThrow("type"))
                        .contains(Telephony.Sms.MESSAGE_TYPE_INBOX + "")) {
                    directionType = DirectionType.INPUT;
                } else {
                    directionType = DirectionType.OUTPUT;
                }

                mMessages.add(new MessageInfo(messageText.replaceAll("\\n", " "), contact, date
                        , /*ContactUtils.contactName(contact)*/"", directionType, cid));
                // use msgData
            } while (cursor.moveToNext());
            cursor.close();
        }
        return mMessages;
    }

    public static ArrayList<MessageInfo> readSmsByContact1(String contact) {
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

                long cid = cursor.getLong(cursor.getColumnIndex("_id"));
//                String photoURI = ContactUtils.getContactPhotoURI(MyApplication.getContext(), contact);
                DirectionType directionType;
                if (cursor.getString(cursor.getColumnIndexOrThrow("type")).contains("1")) {
                    directionType = DirectionType.INPUT;
                } else {
                    directionType = DirectionType.OUTPUT;
                }

                mMessages.add(new MessageInfo(messageText, contact, date
                        , /*ContactUtils.contactName(contact)*/"", directionType, cid));
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

    public static boolean deleteSms(String smsId) {
        boolean isSmsDeleted = false;
        boolean permissionDelete = true;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            if (!Telephony.Sms.getDefaultSmsPackage(MyApplication.getContext()).equals(MainActivity.PACKAGE_NAME)) {
                permissionDelete = false;
                Toast.makeText(MyApplication.getContext(), "This app is not set as your default messaging app.", Toast.LENGTH_LONG).show();
            } else {
                permissionDelete = true;
            }
        }
        if (permissionDelete) {

            try {
                MyApplication.getContext().getContentResolver().delete(
                        Uri.parse("content://sms/" + smsId), null, null);
                isSmsDeleted = true;

            } catch (Exception ex) {
                isSmsDeleted = false;
            }
        }
        return isSmsDeleted;
    }

    public static void printDb(Context context) {
        MessageSelection where = new MessageSelection();
        MessageCursor cCursor = where.query(context);
        if (cCursor.moveToFirst()) {
            do {
                String db = "";
                for (int i = 0; i < cCursor.getColumnCount(); i++) {
                    db += cCursor.getColumnName(i) + " <" + cCursor.getString(i) + "> ";
                    System.out.println(db);
                }
            } while (cCursor.moveToNext());
        }
        cCursor.close();
    }

    public static void copyAllSms() {
        long lastDate = 0;
        Cursor cursor = MyApplication.getContext().getContentResolver().query(Uri.parse("content://sms"), null, "address IS NOT NULL", null, "date");
        if (cursor != null && cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String messageText = cursor.getString(cursor.getColumnIndexOrThrow("body"));
                String contact = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                long date = cursor.getLong(cursor.getColumnIndexOrThrow("date"));
                lastDate = date;
                System.out.println("date : " + date);
                String status = cursor.getString(cursor.getColumnIndexOrThrow("status"));
                String thread_id = cursor.getString(cursor.getColumnIndexOrThrow("thread_id"));
                String cid = cursor.getString(cursor.getColumnIndex("_id"));
                DirectionType directionType;
                if (cursor.getString(cursor.getColumnIndexOrThrow("type"))
                        .contains(Telephony.Sms.MESSAGE_TYPE_INBOX + "")) {
                    directionType = DirectionType.INPUT;
                } else {
                    directionType = DirectionType.OUTPUT;
                }
                MessageContentValues values = new MessageContentValues();
                values.putMessageText(messageText.replaceAll("\\n", " ")).putNumber(contact).putStatus(status)
                        .putType(directionType).putDate(date).putSmsId(cid).putThreadId(thread_id);
                values.insert(MyApplication.getContext());
            } while (cursor.moveToNext());
            cursor.close();
        }

    }

    public static Date convertStringToDate(String dateString) {
        Date date = null;
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            date = df.parse(dateString);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return date;
    }

    public static void checkForCopySMS() {
        long lastDate = 0;
        MessageSelection where = new MessageSelection();
        MessageCursor cursor = where.query(MyApplication.getContext());
        if (cursor != null && cursor.moveToFirst()) {
            long updatedTime = AppPref.getInstance().getUpdatedTim();
            Cursor mCursor = MyApplication.getContext().getContentResolver().query(Uri.parse("content://sms"), null, "address IS NOT NULL and date > '" + updatedTime + "'", null, null);
            if (mCursor != null && mCursor.moveToFirst()) {
                do {
                    String messageText = mCursor.getString(mCursor.getColumnIndexOrThrow("body"));
                    String contact = mCursor.getString(mCursor.getColumnIndexOrThrow("address"));
                    long date = mCursor.getLong(mCursor.getColumnIndexOrThrow("date"));
                    lastDate = date;
                    String status = mCursor.getString(mCursor.getColumnIndexOrThrow("status"));
                    String thread_id = mCursor.getString(mCursor.getColumnIndexOrThrow("thread_id"));
                    String cid = mCursor.getString(mCursor.getColumnIndex("_id"));
                    DirectionType directionType;
                    if (mCursor.getString(mCursor.getColumnIndexOrThrow("type"))
                            .contains(Telephony.Sms.MESSAGE_TYPE_INBOX + "")) {
                        directionType = DirectionType.INPUT;
                    } else {
                        directionType = DirectionType.OUTPUT;
                    }
                    MessageContentValues values = new MessageContentValues();
                    values.putMessageText(messageText.replaceAll("\\n", " ")).putNumber(contact).putStatus(status)
                            .putType(directionType).putDate(date).putSmsId(cid).putThreadId(thread_id);
                    values.insert(MyApplication.getContext());
                } while (mCursor.moveToNext());
                mCursor.close();
            }
            System.currentTimeMillis();
            AppPref.getInstance().setUpdatedTime(System.currentTimeMillis());
        } else {
            copyAllSms();
            AppPref.getInstance().setUpdatedTime(System.currentTimeMillis());
        }
    }
}
