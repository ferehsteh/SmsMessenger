package lb7.alish.smsmessenger.view.messagelist;

import android.Manifest;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.logic.MarshmallowPermission;
import lb7.alish.smsmessenger.logic.sms.SmsUtils;
import lb7.alish.smsmessenger.view.contacts.ContactListFragment;
import lb7.alish.smsmessenger.view.utils.UiUtils;

/**
 * Created by AliSh on 12/8/2016.
 */

public class MessageListFragment extends Fragment {

    ArrayList<MessageInfo> allMessages = new ArrayList<>();
    private String[] requiredPermission = new String[]{Manifest.permission.READ_SMS, Manifest.permission.READ_CONTACTS};
    private RecyclerView recyclerView;
    private FloatingActionButton mFloatingActionButton;
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_home, menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        final SearchView searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchViewAndroidActionBar.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                final ArrayList<MessageInfo> filteredList = new ArrayList<>();
                for (int i = 0; i < allMessages.size(); i++) {
                    MessageInfo messageInfo = allMessages.get(i);
                    final String text = messageInfo.getContact().toLowerCase();
                    if (text.contains(newText)) {
                        filteredList.add(allMessages.get(i));
                    }
                }
                MessagesByContactAdapter adapter = new MessagesByContactAdapter(getActivity(), filteredList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                return false;
            }

        });
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return false;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 1));
        recyclerView.setHasFixedSize(true);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        toolbar.setTitle("Title");
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("AAAA");
//        toolbar.setSubtitle("AAAA");
//        ChangeDefaultApp();


        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        System.out.println("onHiddenChanged : " + hidden);
//        if (!hidden) {
//            UiUtils.setActionBarTitle((MainActivity) getActivity(), "Messages", false);
//        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        onContactFabClick();
        if (MarshmallowPermission.isPermissionGuaranted(requiredPermission)) {
            allMessages = SmsUtils.readAllSms();
            recyclerView.setAdapter(new MessagesByContactAdapter(getActivity(), allMessages));
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_SMS, Manifest.permission.READ_CONTACTS}, 1);
        }
    }

    public void onContactFabClick() {
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.addFragmentToBackStack(getActivity(), new ContactListFragment());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

//    private void ChangeDefaultApp() {
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//            if (!Telephony.Sms.getDefaultSmsPackage(getActivity()).equals(MainActivity.PACKAGE_NAME)) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setMessage("This app is not set as your default messaging app. Do you want to set it as default?")
//                        .setCancelable(false)
//                        .setTitle("Alert!")
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        })
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @TargetApi(19)
//                            public void onClick(DialogInterface dialog, int id) {
//                                Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
//                                intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, getActivity().getPackageName());
//                                startActivity(intent);
//                            }
//                        });
//                builder.show();
//            }
//        }
//    }


}
