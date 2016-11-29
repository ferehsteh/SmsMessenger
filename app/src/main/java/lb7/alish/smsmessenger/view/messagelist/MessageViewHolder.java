package lb7.alish.smsmessenger.view.messagelist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.logic.ContactUtils;
import lb7.alish.smsmessenger.view.utils.TimeUtils;

/**
 * Created by AliSh on 11/29/2016.
 */

public class MessageViewHolder extends RecyclerView.ViewHolder {

    public TextView mContactName;
    public TextView mMessageText;
    public TextView mDateText;
    public View itemView;

    public MessageViewHolder(View view) {
        super(view);
        mContactName = (TextView) view.findViewById(R.id.contactTextView);
        mMessageText = (TextView) view.findViewById(R.id.messageTextView);
        mDateText = (TextView) view.findViewById(R.id.dateTextView);
        itemView = view;
    }

    public void bind(MessageInfo messageInfo) {
        if (messageInfo.getContact()!=null && !messageInfo.getContact().isEmpty()) {
            mContactName.setText(ContactUtils.contactName(messageInfo.getContact()));
        }
        mMessageText.setText(messageInfo.getMessageText());
        mDateText.setText(TimeUtils.getTime(Long.parseLong(messageInfo.getDate())));
    }
}
