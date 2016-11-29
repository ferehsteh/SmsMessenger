package lb7.alish.smsmessenger.view.messagelist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import lb7.alish.smsmessenger.R;

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
    }

    @Override
    public int getItemCount() {
        return mMessageInfos.size();
    }

}
