package lb7.alish.smsmessenger.view.conversation;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.logic.sms.SmsUtils;
import lb7.alish.smsmessenger.view.messagelist.MessageInfo;
import lb7.alish.smsmessenger.view.utils.TimeUtils;

/**
 * Created by AliSh on 11/29/2016.
 */

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder> {
    private ArrayList<MessageInfo> mMessageInfos;
    private Activity mActivity;

    public ConversationAdapter(Activity activity, ArrayList<MessageInfo> messageInfos) {
        mMessageInfos = messageInfos;
        mActivity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 0;
        switch (mMessageInfos.get(position).getDirectionType()) {
            case INPUT:
                break;
            case OUTPUT:
                viewType = 1;
                break;
        }
        return viewType;
    }

    @Override
    public ConversationAdapter.ConversationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.left, parent, false);
        if (viewType == 1) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.right, parent, false);
        }
        return new ConversationViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ConversationAdapter.ConversationViewHolder holder, int position) {
        final int itemType = getItemViewType(position);
        MessageInfo messageInfo = mMessageInfos.get(position);
        holder.bind(mActivity, messageInfo);
    }

    @Override
    public int getItemCount() {
        return mMessageInfos.size();
    }

    public void remove(int position) {
        mMessageInfos.remove(position);
        notifyItemRemoved(position);
    }

    public void add(MessageInfo message) {
        mMessageInfos.add(message);
    }

    public void add(ArrayList<MessageInfo> messages) {
        mMessageInfos.addAll(messages);
    }

    public class ConversationViewHolder extends RecyclerView.ViewHolder {

        private TextView mMessageText;
        private TextView mDateText;
        private View itemView;

        public ConversationViewHolder(View view) {
            super(view);
            mMessageText = (TextView) view.findViewById(R.id.msgr);
            itemView = view;
        }

        public void bind(final Activity activity, MessageInfo messageInfo) {
            final long cid = messageInfo.getId();
            mMessageText.setText(messageInfo.getMessageText() + "\r \n \n" + TimeUtils.getTimeForConversations(Long.parseLong(messageInfo.getDate())));
            mMessageText.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(activity);
                    alert.setMessage(R.string.deleteSMS);
                    alert.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            boolean deleted = SmsUtils.deleteSms(String.valueOf(cid));
                            if (deleted) {
                                int position = getAdapterPosition();
                                remove(position);
                            }
                        }
                    });
                    alert.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            getAdapterPosition();
                        }
                    });
                    alert.create();
                    alert.show();
                    return false;
                }
            });

        }
    }
}
