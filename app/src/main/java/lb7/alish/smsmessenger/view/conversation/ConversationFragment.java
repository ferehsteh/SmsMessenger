package lb7.alish.smsmessenger.view.conversation;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.logic.SimCardUtils;
import lb7.alish.smsmessenger.logic.pref.AppPref;
import lb7.alish.smsmessenger.logic.sms.SmsUtils;
import lb7.alish.smsmessenger.view.messagelist.MessageInfo;
import lb7.alish.smsmessenger.view.messagelist.MessagesByContactAdapter;

public class ConversationFragment extends Fragment {

    public static String PARTY_KEY = "lb7.alish.smsmessenger.view.conversation.ConversationFragment.PARTY_KEY";
    int selectedSim = 1;
    ArrayList<MessageInfo> messageInfos = new ArrayList<>();
    private String mParty = null;
    private Toolbar toolbar;
    private RecyclerView recyclerView;

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle(R.string.new_title);
        if (mParty != null) {
//            UiUtils.setActionBarTitle((MainActivity) getActivity(), ContactUtils.contactName(mParty), true);

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.conversation_menu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search1);
        final SearchView searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchViewAndroidActionBar.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //TODO
                //bug dare
                newText = newText.toLowerCase();
                final ArrayList<MessageInfo> filteredList = new ArrayList<>();
                for (int i = 0; i < messageInfos.size(); i++) {
                    MessageInfo messageInfo = messageInfos.get(i);
                    final String text = messageInfo.getMessageText().toLowerCase();
                    if (text.contains(newText)) {
                        filteredList.add(messageInfos.get(i));
                    }
                }
                MessagesByContactAdapter adapter = new MessagesByContactAdapter(getActivity(), filteredList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                return false;
            }

        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem item3 = menu.findItem(R.id.action_search);
        item3.setVisible(false);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_call:
                String phone = mParty;
                makeCall(phone);
                break;
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_conversation, container, false);

        if (getArguments() != null && getArguments().getString(PARTY_KEY) != null) {
            mParty = getArguments().getString(PARTY_KEY);
        }
        System.out.println("mParty : " + mParty);
//        UiUtils.setActionBarTitle((MainActivity) getActivity(), ContactUtils.contactName(mParty), true);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
        messageInfos = SmsUtils.readSmsByContact(mParty);
        recyclerView.setAdapter(new ConversationAdapter(getActivity(), messageInfos));
//        recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                return false;
//            }
//        });
        Button sendButton = (Button) view.findViewById(R.id.send_button);
        final Button simCardButton = (Button) view.findViewById(R.id.sim_card_button);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationIcon(R.drawable.ic_send_black_24dp);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().onBackPressed();
//
//            }
//        });

        boolean isDualSim = SimCardUtils.isDualSim();
        if (isDualSim) {
            simCardButton.setVisibility(View.VISIBLE);
            selectedSim = AppPref.getInstance().getSelectedSim();
            simCardButton.setText("" + selectedSim);

            simCardButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int lastSelectedSim = AppPref.getInstance().getSelectedSim();
                    if (lastSelectedSim == 1) {
                        AppPref.getInstance().setSelectedSim(2);
                        simCardButton.setText("2");
                        selectedSim = 2;
                    } else {
                        AppPref.getInstance().setSelectedSim(1);
                        simCardButton.setText("1");
                        selectedSim = 1;
                    }
                }
            });
        }

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText messageEditView = (EditText) view.findViewById(R.id.message_text_view);
                String message = messageEditView.getText().toString();
                if (!message.isEmpty()) {
                    SmsUtils.sendMessage(mParty, message, selectedSim);
                    messageInfos = SmsUtils.readSmsByContact(mParty);
                    ConversationAdapter adapter = new ConversationAdapter(getActivity(), messageInfos);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    messageEditView.setText("");
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(messageEditView.getWindowToken(), 0);
                }
            }
        });

        return view;
    }

    private void makeCall(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(intent);
    }


}
