package lb7.alish.smsmessenger.view.conversation;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.logic.sms.SmsUtils;
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

    public void bind(final Activity activity, MessageInfo messageInfo) {
        final long cid = messageInfo.getId();
//        switch (messageInfo.getDirectionType()) {
//            case INPUT:
//                itemView.setBackgroundColor(ContextCompat.getColor(MyApplication.getContext(), R.color.input_color));
//                break;
//            case OUTPUT:
//                itemView.setBackgroundColor(ContextCompat.getColor(MyApplication.getContext(), R.color.output_color));
//                break;
//        }
        mMessageText.setText(messageInfo.getMessageText() + "\r \n" + TimeUtils.getTimeForConversations(Long.parseLong(messageInfo.getDate())));
//        mDateText.setText(TimeUtils.getTimeForConversations(Long.parseLong(messageInfo.getDate())));
        mMessageText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(activity);
                alert.setMessage(R.string.deleteSMS);
                alert.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SmsUtils.deleteSms(String.valueOf(cid));
                    }
                });
                alert.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.create();
                alert.show();
                return false;
            }
        });
    }
}
