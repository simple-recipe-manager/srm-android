package org.bdawg.simplerecipemanager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;

import org.bdawg.simplerecipemanager.utils.PrefrencesUtil;
import org.parceler.Parcels;

import ly.whisk.model.BaseUser;

/**
 * Created by breland on 3/10/2015.
 */
public class BaseActivity extends Activity {

    private PrefrencesUtil prefrences;
    private BaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.prefrences = new PrefrencesUtil(this);
        getIntent().setExtrasClassLoader(BaseUser.class.getClassLoader());
        BaseUser userExtra = (BaseUser) getIntent().getSerializableExtra(BaseUser.USER_EXTRA_KEY);
        if (userExtra == null) {
            this.user = new BaseUser();
        } else {
            this.user = userExtra;
        }
    }

    public PrefrencesUtil getPrefrences() {
        return this.prefrences;
    }

    public BaseUser getUser() {
        return this.user;
    }

    public void setUser(BaseUser user) {
        this.user = user;
    }
}
