package lb7.alish.smsmessenger.view.contacts;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.logic.contacts.ContactUtils;
import lb7.alish.smsmessenger.view.messagelist.MainActivity;
import lb7.alish.smsmessenger.view.utils.UiUtils;

/**
 * Created by AliSh on 12/8/2016.
 */

public class ContactListFragment extends Fragment {

    @Override
    public void onResume() {
        super.onResume();
        UiUtils.setActionBarTitle((MainActivity) getActivity(), "Contacts", true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_contact_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 1));
        recyclerView.setHasFixedSize(true);
        ArrayList<ContactInfo> contacts = ContactUtils.getAllContacts();
        recyclerView.setAdapter(new ContactsAdapter(getActivity(), contacts));

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
