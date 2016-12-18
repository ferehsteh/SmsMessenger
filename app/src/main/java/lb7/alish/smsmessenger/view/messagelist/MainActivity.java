package lb7.alish.smsmessenger.view.messagelist;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import lb7.alish.smsmessenger.MyApplication;
import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.logic.MarshmallowPermission;
import lb7.alish.smsmessenger.logic.sms.SmsUtils;
import lb7.alish.smsmessenger.view.contacts.ContactListActivity;
import lb7.alish.smsmessenger.view.utils.UiUtils;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String[] requiredPermission = new String[]{Manifest.permission.READ_SMS, Manifest.permission.READ_CONTACTS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (MyApplication.getInstance().isRunFragmentBase) {
            setContentView(R.layout.activity_main);
            UiUtils.startFragment(this, new MessageListFragment());
        } else {


//
            setContentView(R.layout.main_fragment);
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

            if (MarshmallowPermission.isPermissionGuaranted(requiredPermission)) {
                ArrayList<MessageInfo> allMessages = SmsUtils.readAllSms();
                recyclerView.setAdapter(new MessagesByContactAdapter(this, allMessages));
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_SMS, Manifest.permission.READ_CONTACTS}, 1);
            }
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ContactListActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (MarshmallowPermission.isPermissionGuaranted(requiredPermission)) {
            ArrayList<MessageInfo> allMessages = SmsUtils.readAllSms();
            recyclerView.setAdapter(new MessagesByContactAdapter(this, allMessages));
        } else {
            Toast.makeText(this, "This application need sms permission for running", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    public void addFragmentToBackStack(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contentFragment, fragment);
        transaction.addToBackStack(fragment.getTag());
        transaction.commit();
    }

    public void removeFragmentToBackStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            case R.id.action_call:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
