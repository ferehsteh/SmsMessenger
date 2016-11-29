package lb7.alish.smsmessenger.view.messagelist;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import lb7.alish.smsmessenger.MyApplication;
import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.view.conversation.ConversationActivity;

/**
 * Created by AliSh on 11/24/2016.
 */

public class MessagesByContactAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private ArrayList<MessageInfo> mMessageInfos;

    public MessagesByContactAdapter(ArrayList<MessageInfo> messageInfos) {
        mMessageInfos = messageInfos;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MessageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MessageViewHolder holder, int position) {
        final MessageInfo messageInfo = mMessageInfos.get(position);
        holder.bind(messageInfo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyApplication.getContext(), ConversationActivity.class);
                intent.putExtra(ConversationActivity.PARTY_KEY, messageInfo.getContact());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMessageInfos.size();
    }

}
