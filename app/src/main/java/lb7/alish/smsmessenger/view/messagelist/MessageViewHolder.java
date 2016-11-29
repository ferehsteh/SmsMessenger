package lb7.alish.smsmessenger.view.messagelist;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import lb7.alish.smsmessenger.MyApplication;
import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.logic.ContactUtils;
import lb7.alish.smsmessenger.view.conversation.ConversationActivity;
import lb7.alish.smsmessenger.view.utils.TimeUtils;

/**
 * Created by AliSh on 11/29/2016.
 */

public class MessageViewHolder extends RecyclerView.ViewHolder {

    private TextView mContactName;
    private TextView mMessageText;
    private TextView mDateText;
    private View itemView;

    public MessageViewHolder(View view) {
        super(view);
        mContactName = (TextView) view.findViewById(R.id.contactTextView);
        mMessageText = (TextView) view.findViewById(R.id.messageTextView);
        mDateText = (TextView) view.findViewById(R.id.dateTextView);
        itemView = view;
    }

    public void bind(final MessageInfo messageInfo) {
        final String contactNumber = messageInfo.getContact();
        if (contactNumber != null && !contactNumber.isEmpty()) {
            mContactName.setText(ContactUtils.contactName(contactNumber));
        }
        mMessageText.setText(messageInfo.getMessageText());
        mDateText.setText(TimeUtils.getTime(Long.parseLong(messageInfo.getDate())));
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyApplication.getContext(), ConversationActivity.class);
                intent.putExtra(ConversationActivity.PARTY_KEY, contactNumber);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContext().startActivity(intent);
            }
        });
    }
}
