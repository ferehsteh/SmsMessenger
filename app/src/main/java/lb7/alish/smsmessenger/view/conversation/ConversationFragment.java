package lb7.alish.smsmessenger.view.conversation;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.logic.SimCardUtils;
import lb7.alish.smsmessenger.logic.pref.AppPref;
import lb7.alish.smsmessenger.logic.sms.SmsUtils;

public class ConversationFragment extends Fragment {

    public static String PARTY_KEY = "lb7.alish.smsmessenger.view.conversation.ConversationFragment.PARTY_KEY";
    int selectedSim = 1;
    private String mParty = null;
    private Toolbar toolbar;

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
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_call:
                String phone = mParty;
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
                break;
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
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
        recyclerView.setAdapter(new ConversationAdapter(SmsUtils.readSmsByContact(mParty)));
        Button sendButton = (Button) view.findViewById(R.id.send_button);
        final Button simCardButton = (Button) view.findViewById(R.id.sim_card_button);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                    recyclerView.setAdapter(new ConversationAdapter(SmsUtils.readSmsByContact(mParty)));
                    messageEditView.setText("");
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(messageEditView.getWindowToken(), 0);
                }
            }
        });

        return view;
    }

    private void makeCall() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + mParty));
        startActivity(intent);
    }
}
