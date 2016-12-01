package lb7.alish.smsmessenger.view.contacts;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import lb7.alish.smsmessenger.MyApplication;
import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.view.conversation.ConversationActivity;

/**
 * Created by AliSh on 11/29/2016.
 */

public class ContactViewHolder extends RecyclerView.ViewHolder {

    private TextView mMessageText;
    private TextView mDateText;
    private View itemView;

    public ContactViewHolder(View view) {
        super(view);
        mMessageText = (TextView) view.findViewById(R.id.messageTextView);
        mDateText = (TextView) view.findViewById(R.id.dateTextView);
        itemView = view;
    }

    public void bind(final ContactInfo messageInfo) {
        mMessageText.setText(messageInfo.getName());
        mDateText.setText(messageInfo.getPhoneNumber());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyApplication.getContext(), ConversationActivity.class);
                intent.putExtra(ConversationActivity.PARTY_KEY, messageInfo.getPhoneNumber());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContext().startActivity(intent);
            }
        });
    }
}
