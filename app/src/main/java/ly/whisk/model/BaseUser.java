package ly.whisk.model;

import org.parceler.Parcel;

import java.io.Serializable;

/**
 * Created by breland on 3/15/15.
 */
@Parcel
public class BaseUser implements Serializable {

    public static String USER_EXTRA_KEY = "USER";
    String id;
    String name;

    public BaseUser() {

    }

    public BaseUser(String id, String name){
        this.id = id;
        this.name = name;
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

}