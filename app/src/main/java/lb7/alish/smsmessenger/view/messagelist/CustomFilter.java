package lb7.alish.smsmessenger.view.messagelist;


import android.widget.Filter;

import java.util.ArrayList;


/**
 * Created by Zargaran on 12/18/2016.
 */

public class CustomFilter extends Filter {
    private ArrayList<MessageInfo> contactList;
    private ArrayList<MessageInfo> filteredContactList;
    private MessagesByContactAdapter adapter;

    public CustomFilter(ArrayList<MessageInfo> contactList, MessagesByContactAdapter adapter) {
        this.adapter = adapter;
        this.contactList = contactList;
        this.filteredContactList = new ArrayList();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        filteredContactList.clear();
        final FilterResults results = new FilterResults();

        //here you need to add proper items do filteredContactList
        for (final MessageInfo item : contactList) {
            if (item.getContact().toLowerCase().trim().contains(constraint)) {
                filteredContactList.add(item);
            }
        }

        results.values = filteredContactList;
        results.count = filteredContactList.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.setList(filteredContactList);
        adapter.notifyDataSetChanged();
    }
}