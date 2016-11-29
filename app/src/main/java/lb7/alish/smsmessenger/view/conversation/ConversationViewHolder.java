package lb7.alish.smsmessenger.view.conversation;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import lb7.alish.smsmessenger.MyApplication;
import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.view.messagelist.MessageInfo;
import lb7.alish.smsmessenger.view.utils.TimeUtils;

/**
 * Created by AliSh on 11/29/2016.
 */

public class ConversationViewHolder extends RecyclerView.ViewHolder {

    public TextView mContactName;
    public TextView mMessageText;
    public TextView mDateText;
    public View itemView;

    public ConversationViewHolder(View view) {
        super(view);
        mContactName = (TextView) view.findViewById(R.id.contactTextView);
        mMessageText = (TextView) view.findViewById(R.id.messageTextView);
        mDateText = (TextView) view.findViewById(R.id.dateTextView);
        itemView = view;
    }

    public void bind(MessageInfo messageInfo) {
        switch (messageInfo.getDirectionType()) {
            case INPUT:
                itemView.setBackgroundColor(ContextCompat.getColor(MyApplication.getContext(), R.color.input_color));
                break;
            case OUTPUT:
                itemView.setBackgroundColor(ContextCompat.getColor(MyApplication.getContext(), R.color.output_color));
                break;
        }
        mMessageText.setText(messageInfo.getMessageText());
        mDateText.setText(TimeUtils.getTime(Long.parseLong(messageInfo.getDate())));
    }
}
