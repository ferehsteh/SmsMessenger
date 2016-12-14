package lb7.alish.smsmessenger.view.contacts;

import android.graphics.Bitmap;

/**
 * Created by AliSh on 12/1/2016.
 */

public class ContactInfo {

    private String mName;
    private String mPhoneNumber;
    private Bitmap mThumb;

    public ContactInfo(String name, String phoneNumber, Bitmap thumb) {
        mName = name;
        mPhoneNumber = phoneNumber;
        mThumb = thumb;
    }

    public String getName() {
        return mName;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public Bitmap getThumb() {
        return mThumb;
    }

    public void setThumb(Bitmap thumb) {
        this.mThumb = thumb;
    }
}
