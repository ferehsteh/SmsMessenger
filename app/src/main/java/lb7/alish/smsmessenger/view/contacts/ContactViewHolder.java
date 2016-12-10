package lb7.alish.smsmessenger.view.contacts;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.view.conversation.ConversationFragment;
import lb7.alish.smsmessenger.view.utils.UiUtils;

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

    public void bind(final Activity activity, final ContactInfo messageInfo) {
        mMessageText.setText(messageInfo.getName());
        mDateText.setText(messageInfo.getPhoneNumber());
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
