package lb7.alish.smsmessenger.view.messagelist;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import java.io.FileNotFoundException;

import lb7.alish.smsmessenger.MyApplication;
import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.logic.contacts.ContactUtils;
import lb7.alish.smsmessenger.view.contacts.ContactInfo;
import lb7.alish.smsmessenger.view.conversation.ConversationActivity;
import lb7.alish.smsmessenger.view.conversation.ConversationFragment;
import lb7.alish.smsmessenger.view.utils.TimeUtils;
import lb7.alish.smsmessenger.view.utils.UiUtils;

/**
 * Created by AliSh on 11/29/2016.
 */

public class MessageViewHolder extends RecyclerView.ViewHolder {

    QuickContactBadge badge;
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
//        badge = (QuickContactBadge) view.findViewById(R.id.pic);
        // itemView = view;
    }

    public void bind(final Activity activity, final MessageInfo messageInfo) {
        final String contactNumber = messageInfo.getContact();
        ContactInfo contact = ContactUtils.getContact(contactNumber);
//        final Typeface tf  = Typeface.createFromAsset(activity.getAssets(),"BMitraBd.ttf");
//        mMessageText.setTypeface(tf);
//        mContactName.setTypeface(tf);
//        mDateText.setTypeface(tf);
        if (contact != null) {
            mContactName.setText(contact.getName());

            if (contact.getThumbnail() != null && !contact.getThumbnail().isEmpty()) {
                try {
                    mContactPic.setImageURI(Uri.parse(contact.getThumbnail()));
                    throw new FileNotFoundException();
                }  catch(FileNotFoundException ex) {
                    mContactPic.setImageResource(R.mipmap.contact_pic);
                }

            } else {
                mContactPic.setImageResource(R.mipmap.contact_pic);
            }
        }
        mMessageText.setText(messageInfo.getMessageText());
        mDateText.setText(TimeUtils.getTime(Long.parseLong(messageInfo.getDate())));

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
        mContactPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", contactNumber, null));
                activity.startActivity(intent);
            }
        });
    }
}
