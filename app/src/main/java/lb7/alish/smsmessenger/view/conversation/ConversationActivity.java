package lb7.alish.smsmessenger.view.conversation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.logic.SmsUtils;

public class ConversationActivity extends AppCompatActivity {

    public static String PARTY_KEY = "lb7.alish.smsmessenger.view.conversation.ConversationActivity";
    private String mParty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (getIntent() != null && getIntent().getStringExtra(PARTY_KEY) != null) {
            mParty = getIntent().getStringExtra(PARTY_KEY);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(new ConversationAdapter(SmsUtils.readSmsByContact(mParty)));
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
