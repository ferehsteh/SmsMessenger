package lb7.alish.smsmessenger.view.contacts;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import lb7.alish.smsmessenger.R;

/**
 * Created by AliSh on 11/29/2016.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactViewHolder> {
    private ArrayList<ContactInfo> mMessageInfos;
    private Activity mActivity;

    public ContactsAdapter(Activity activity, ArrayList<ContactInfo> messageInfos) {
        mMessageInfos = messageInfos;
        mActivity = activity;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.conversation_item, parent, false);
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, int position) {
        holder.bind(mActivity, mMessageInfos.get(position));
    }

    @Override
    public int getItemCount() {
        return mMessageInfos.size();
    }
}
