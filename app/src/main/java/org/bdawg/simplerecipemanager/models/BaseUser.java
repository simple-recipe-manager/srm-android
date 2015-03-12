package org.bdawg.simplerecipemanager.models;

import java.io.Serializable;

/**
 * Created by breland on 2/24/15.
 */
public class BaseUser implements Serializable{

    public static String USER_EXTRA_KEY = "USER";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    private String name;
    private String postalCode;

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
