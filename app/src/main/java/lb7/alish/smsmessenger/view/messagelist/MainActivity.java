package lb7.alish.smsmessenger.view.messagelist;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
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
import lb7.alish.smsmessenger.logic.pref.AppPref;
import lb7.alish.smsmessenger.logic.sms.SmsUtils;
import lb7.alish.smsmessenger.view.contacts.ContactListActivity;
import lb7.alish.smsmessenger.view.utils.UiUtils;

public class MainActivity extends AppCompatActivity {

    public static String PACKAGE_NAME;
    private RecyclerView recyclerView;
    private String[] requiredPermission = new String[]{Manifest.permission.READ_SMS, Manifest.permission.READ_CONTACTS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PACKAGE_NAME = getApplicationContext().getPackageName();
        if (AppPref.getInstance().getUpdatedTim() == 0L) {
            AppPref.getInstance().setUpdatedTime(System.currentTimeMillis());
        }
        if (MyApplication.getInstance().isRunFragmentBase) {
            setContentView(R.layout.activity_main);
            SmsUtils.checkForCopySMS();
//            SmsUtils.copyAllSms();
            ChangeDefaultApp();
            UiUtils.startFragment(this, new MessageListFragment());
        } else {
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
                UiUtils.startFragment(this, new MessageListFragment());
                break;
            case R.id.action_call:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        UiUtils.startFragment(this, new MessageListFragment());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void ChangeDefaultApp() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            if (!Telephony.Sms.getDefaultSmsPackage(this).equals(PACKAGE_NAME)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("This app is not set as your default messaging app. Do you want to set it as default?")
                        .setCancelable(false)
                        .setTitle("Alert!")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @TargetApi(19)
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
                                intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, getPackageName());
                                startActivity(intent);
                            }
                        });
                builder.show();
            }
        }
    }

    private void CheckReplicateSMS() {

    }

}
