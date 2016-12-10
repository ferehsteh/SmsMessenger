package lb7.alish.smsmessenger.view.messagelist;

import android.Manifest;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

    private String[] requiredPermission = new String[]{Manifest.permission.READ_SMS, Manifest.permission.READ_CONTACTS};
    private RecyclerView recyclerView;
    private FloatingActionButton mFloatingActionButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 1));
        recyclerView.setHasFixedSize(true);
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
            ArrayList<MessageInfo> allMessages = SmsUtils.readAllSms();
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

}
