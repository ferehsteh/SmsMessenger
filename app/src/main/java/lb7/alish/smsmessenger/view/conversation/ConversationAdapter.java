package lb7.alish.smsmessenger.view.conversation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.view.messagelist.MessageInfo;
import lb7.alish.smsmessenger.view.messagelist.MessageViewHolder;

/**
 * Created by AliSh on 11/29/2016.
 */

public class ConversationAdapter extends RecyclerView.Adapter<MessageViewHolder> {
    private ArrayList<MessageInfo> mMessageInfos;

    public ConversationAdapter(ArrayList<MessageInfo> messageInfos) {
        mMessageInfos = messageInfos;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.conversation_item, parent, false);
        return new MessageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MessageViewHolder holder, int position) {
        holder.bind(mMessageInfos.get(position));
    }

    @Override
    public int getItemCount() {
        return mMessageInfos.size();
    }
}
