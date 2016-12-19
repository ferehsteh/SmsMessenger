package lb7.alish.smsmessenger.view.contacts;


import java.io.InputStream;

/**
 * Created by AliSh on 12/1/2016.
 */

public class ContactInfo {

    private String mName;
    private String mPhoneNumber;
    private String mThumbnailURI;
    private InputStream mStream;

    public ContactInfo(String name, String phoneNumber, String thumbnailUri) {
        mName = name;
        mPhoneNumber = phoneNumber;
        mThumbnailURI = thumbnailUri;
//        mStream = stream;
    }

    public String getName() {
        return mName;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public String getThumbnail() {
        return mThumbnailURI;
    }

//    public InputStream getStream() {
//        return mStream;
//    }
}
