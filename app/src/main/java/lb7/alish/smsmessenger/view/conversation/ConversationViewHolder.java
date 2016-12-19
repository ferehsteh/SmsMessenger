package lb7.alish.smsmessenger.view.conversation;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

    private TextView mMessageText;
    private TextView mDateText;
    private View itemView;

    public ConversationViewHolder(View view) {
        super(view);
        mMessageText = (TextView) view.findViewById(R.id.msgr);
//        mDateText = (TextView) view.findViewById(R.id.dateTextView);
        itemView = view;
    }

    public void bind(MessageInfo messageInfo) {
//        switch (messageInfo.getDirectionType()) {
//            case INPUT:
//                itemView.setBackgroundColor(ContextCompat.getColor(MyApplication.getContext(), R.color.input_color));
//                break;
//            case OUTPUT:
//                itemView.setBackgroundColor(ContextCompat.getColor(MyApplication.getContext(), R.color.output_color));
//                break;
//        }
        mMessageText.setText(messageInfo.getMessageText());
//        mDateText.setText(TimeUtils.getTimeForConversations(Long.parseLong(messageInfo.getDate())));
    }
}
