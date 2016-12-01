package lb7.alish.smsmessenger.view.messagelist;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.logic.MarshmallowPermission;
import lb7.alish.smsmessenger.logic.sms.SmsUtils;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String[] requiredPermission = new String[]{Manifest.permission.READ_SMS, Manifest.permission.READ_CONTACTS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        if (MarshmallowPermission.isPermissionGuaranted(requiredPermission)) {
            ArrayList<MessageInfo> allMessages = SmsUtils.readAllSms();
            recyclerView.setAdapter(new MessagesByContactAdapter(allMessages));
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_SMS, Manifest.permission.READ_CONTACTS}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (MarshmallowPermission.isPermissionGuaranted(requiredPermission)) {
            ArrayList<MessageInfo> allMessages = SmsUtils.readAllSms();
            recyclerView.setAdapter(new MessagesByContactAdapter(allMessages));
        } else {
            Toast.makeText(this, "This application need sms permission for running", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
