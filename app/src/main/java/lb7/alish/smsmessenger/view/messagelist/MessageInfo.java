package lb7.alish.smsmessenger.view.messagelist;

import android.graphics.Bitmap;

import lb7.alish.smsmessenger.logic.DirectionType;

/**
 * Created by AliSh on 11/29/2016.
 */

public class MessageInfo {

    private String mContact;
    private String mMessageText;
    private String mDisplayName;
    private DirectionType mDirectionType;
    private String mDate;
    private MessageState mMessageState;
    private Bitmap mThumb;


    public MessageInfo(String message, String contact, String date, String displayName, DirectionType directionType
            /*, MessageState messageState*/, Bitmap thumb) {
        mMessageText = message;
        mContact = contact;
        mDirectionType = directionType;
        mDate = date;
        mDisplayName = displayName;
        mThumb = thumb;
        //mMessageState = messageState;
    }

    public String getContact() {
        return mContact;
    }

    public String getMessageText() {
        return mMessageText;
    }

    public DirectionType getDirectionType() {
        return mDirectionType;
    }

    public String getDate() {
        return mDate;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public MessageState getMessageState() {
        return mMessageState;
    }

    public Bitmap getThumb() {
        return mThumb;
    }

    public void setThumb(Bitmap thumb) {
        this.mThumb = thumb;
    }
}
