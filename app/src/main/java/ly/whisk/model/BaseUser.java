package ly.whisk.model;

import org.parceler.Parcel;

/**
 * Created by breland on 3/15/15.
 */
@Parcel
public class BaseUser {

    public static String USER_EXTRA_KEY = "USER";
    String id;
    String name;
    String postalCode;

    public BaseUser() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}