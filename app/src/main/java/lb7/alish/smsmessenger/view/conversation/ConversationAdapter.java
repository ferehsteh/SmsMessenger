package lb7.alish.smsmessenger.view.conversation;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.view.messagelist.MessageInfo;

/**
 * Created by AliSh on 11/29/2016.
 */

public class ConversationAdapter extends RecyclerView.Adapter<ConversationViewHolder> {
    private ArrayList<MessageInfo> mMessageInfos;
    private Activity mActivity;

    public ConversationAdapter(Activity activity, ArrayList<MessageInfo> messageInfos) {
        mMessageInfos = messageInfos;
        mActivity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType=0;
        switch (mMessageInfos.get(position).getDirectionType()) {
            case INPUT:
                break;
            case OUTPUT:
                viewType=1;
                break;
        }
        return viewType;
    }

    @Override
    public ConversationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.left, parent, false);
        if(viewType==1){
            v= LayoutInflater.from(parent.getContext()).inflate(R.layout.right, parent, false);
        }
        return new ConversationViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ConversationViewHolder holder, int position) {
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

}
