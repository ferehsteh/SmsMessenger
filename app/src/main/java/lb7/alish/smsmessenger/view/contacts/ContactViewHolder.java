package lb7.alish.smsmessenger.view.contacts;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import java.io.IOException;

import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.view.conversation.ConversationFragment;
import lb7.alish.smsmessenger.view.utils.UiUtils;

/**
 * Created by AliSh on 11/29/2016.
 */

public class ContactViewHolder extends RecyclerView.ViewHolder {

    QuickContactBadge badge;
    private TextView mNumberText;
    private TextView mNameText;
    private ImageView mContactPic;
//    private View itemView;

    public ContactViewHolder(View view) {
        super(view);
        mNumberText = (TextView) view.findViewById(R.id.numberTextView);
        mNameText = (TextView) view.findViewById(R.id.nameTextView);
        mContactPic = (ImageView) view.findViewById(R.id.contactPic);
//        itemView = view;
    }

    public void bind(final Activity activity, final ContactInfo messageInfo) {
        mNameText.setText(messageInfo.getName());
        mNumberText.setText(messageInfo.getPhoneNumber());
        if (messageInfo.getThumbnail() != null && !messageInfo.getThumbnail().isEmpty()) {
            try {
                mContactPic.setImageURI(Uri.parse(messageInfo.getThumbnail()));
            } catch (Exception e) {
                mContactPic.setImageResource(R.mipmap.contact_pic);
            }
        }else {
            mContactPic.setImageResource(R.mipmap.contact_pic);
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConversationFragment fragment = new ConversationFragment();
                Bundle bundle = new Bundle();
                bundle.putString(ConversationFragment.PARTY_KEY, messageInfo.getPhoneNumber());
                fragment.setArguments(bundle);
                UiUtils.addFragmentToBackStack(activity, fragment);
            }
        });
    }
}
