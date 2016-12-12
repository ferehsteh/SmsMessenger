package lb7.alish.smsmessenger.view.utils;

import android.app.Activity;
import android.app.Fragment;

import lb7.alish.smsmessenger.R;
import lb7.alish.smsmessenger.view.messagelist.MainActivity;

/**
 * Created by AliSh on 12/8/2016.
 */

public class UiUtils {

    public static void startFragment(Activity activity, Fragment fragment) {
        android.app.FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.contentFragment, fragment)
//                .setCustomAnimations(R.animator.slide_in_right_anim, R.animator.slide_in_left_anim)
                // Add this transaction to the back stack
//                .addToBackStack(fragment.getTag())
                .commit();
    }

    public static void addFragmentToBackStack(Activity activity, Fragment fragment) {
        android.app.FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.slide_in_right_anim, R.animator.slide_in_left_anim)
                .add(R.id.contentFragment, fragment)
                // Add this transaction to the back stack
                .addToBackStack(fragment.getTag())
                .commit();
        fragmentManager.executePendingTransactions();
    }

    public static void setActionBarTitle(MainActivity activity, String title, boolean isNeedBackButton) {
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setTitle(title);
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(isNeedBackButton);
        }
    }


}
