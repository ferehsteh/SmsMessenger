package lb7.alish.smsmessenger.view.contacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.logic.contacts.ContactUtils;

public class ContactListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ArrayList<ContactInfo> contacts = ContactUtils.getAllContacts();
        System.out.println("contact size: " + contacts.size());
        mRecyclerView.setAdapter(new ContactsAdapter(contacts));

    }

}
