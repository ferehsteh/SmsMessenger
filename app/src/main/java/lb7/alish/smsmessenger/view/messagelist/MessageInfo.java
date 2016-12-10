package lb7.alish.smsmessenger.view.messagelist;

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

    public MessageInfo(String message, String contact, String date, String displayName, DirectionType directionType
            /*, MessageState messageState*/) {
        mMessageText = message;
        mContact = contact;
        mDirectionType = directionType;
        mDate = date;
        mDisplayName = displayName;
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
}
