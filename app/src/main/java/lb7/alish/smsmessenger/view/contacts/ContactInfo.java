package lb7.alish.smsmessenger.view.contacts;

/**
 * Created by AliSh on 12/1/2016.
 */

public class ContactInfo {

    private String mName;
    private String mPhoneNumber;

    public ContactInfo(String name, String phoneNumber) {
        mName = name;
        mPhoneNumber = phoneNumber;
    }

    public String getName() {
        return mName;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }
}
