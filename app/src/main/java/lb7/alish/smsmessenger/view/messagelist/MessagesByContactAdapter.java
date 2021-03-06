package lb7.alish.smsmessenger.view.messagelist;

import android.app.Activity;
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
    private ArrayList<MessageInfo> messageInfo;
    private Activity mActivity;
    private CustomFilter filter;
    private ArrayList<MessageInfo> mContactFilter;

    public MessagesByContactAdapter(Activity activity, ArrayList<MessageInfo> messageInfos) {
        mMessageInfos = messageInfos;
        mActivity = activity;
        filter = new CustomFilter(messageInfos, this);
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item1, parent, false);
        return new MessageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MessageViewHolder holder, int position) {
        final MessageInfo messageInfo = mMessageInfos.get(position);
        holder.bind(mActivity, messageInfo);
    }

    @Override
    public int getItemCount() {
        return mMessageInfos.size();
    }

    // set adapter filtered list
    public void setList(ArrayList<MessageInfo> list) {
        this.mContactFilter = list;
    }

    //call when you want to filter
    public void filterList(String text) {
        filter.filter(text);
    }


}
