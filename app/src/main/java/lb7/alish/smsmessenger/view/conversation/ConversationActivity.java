package lb7.alish.smsmessenger.view.conversation;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.logic.ContactUtils;
import lb7.alish.smsmessenger.logic.SmsUtils;

public class ConversationActivity extends AppCompatActivity {

    public static String PARTY_KEY = "lb7.alish.smsmessenger.view.conversation.ConversationActivity.PARTY_KEY";
    private String mParty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        if (getIntent() != null && getIntent().getStringExtra(PARTY_KEY) != null) {
            mParty = getIntent().getStringExtra(PARTY_KEY);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(ContactUtils.contactName(mParty));
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(new ConversationAdapter(SmsUtils.readSmsByContact(mParty)));
        Button sendButton = (Button) findViewById(R.id.send_button);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText messageEditView = (EditText) findViewById(R.id.message_text_view);
                String message = messageEditView.getText().toString();
                if (!message.isEmpty()) {
                    SmsUtils.sendMessage(mParty, message);
                    recyclerView.setAdapter(new ConversationAdapter(SmsUtils.readSmsByContact(mParty)));
                    messageEditView.setText("");
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(messageEditView.getWindowToken(), 0);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
