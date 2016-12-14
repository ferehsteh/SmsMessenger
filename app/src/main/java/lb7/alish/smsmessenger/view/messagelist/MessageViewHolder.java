package lb7.alish.smsmessenger.view.messagelist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import lb7.alish.smsmessenger.MyApplication;
import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.logic.contacts.ContactUtils;
import lb7.alish.smsmessenger.view.conversation.ConversationActivity;
import lb7.alish.smsmessenger.view.conversation.ConversationFragment;
import lb7.alish.smsmessenger.view.utils.TimeUtils;
import lb7.alish.smsmessenger.view.utils.UiUtils;

/**
 * Created by AliSh on 11/29/2016.
 */

public class MessageViewHolder extends RecyclerView.ViewHolder {

    private TextView mContactName;
    private TextView mMessageText;
    private TextView mDateText;
    // private View itemView;
    private ImageView mContactPic;

    public MessageViewHolder(View view) {
        super(view);
        mContactName = (TextView) view.findViewById(R.id.contactTextView);
        mMessageText = (TextView) view.findViewById(R.id.messageTextView);
        mDateText = (TextView) view.findViewById(R.id.dateTextView);
        mContactPic = (ImageView) view.findViewById(R.id.pic);
        // itemView = view;
    }

    public void bind(final Activity activity, final MessageInfo messageInfo) {
        final String contactNumber = messageInfo.getContact();
        if (contactNumber != null && !contactNumber.isEmpty()) {
            mContactName.setText(ContactUtils.contactName(contactNumber));
        }
        mMessageText.setText(messageInfo.getMessageText());
        mDateText.setText(TimeUtils.getTime(Long.parseLong(messageInfo.getDate())));
        // Set image if exists
        try {

            if (messageInfo.getThumb() != null) {
                mContactPic.setImageBitmap(messageInfo.getThumb());
            } else {
                mContactPic.setImageResource(R.mipmap.contact_pic);
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getInstance().isRunFragmentBase) {
                    ConversationFragment fragment = new ConversationFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(ConversationFragment.PARTY_KEY, contactNumber);
                    fragment.setArguments(bundle);
                    UiUtils.addFragmentToBackStack(activity, fragment);
                } else {
                    Intent intent = new Intent(MyApplication.getContext(), ConversationActivity.class);
                    intent.putExtra(ConversationActivity.PARTY_KEY, contactNumber);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MyApplication.getContext().startActivity(intent);
                }
            }
        });
    }
}
