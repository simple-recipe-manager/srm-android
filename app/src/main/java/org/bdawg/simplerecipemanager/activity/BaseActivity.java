package org.bdawg.simplerecipemanager.activity;

import android.app.Activity;
import android.os.Bundle;

import org.bdawg.simplerecipemanager.models.BaseUser;
import org.bdawg.simplerecipemanager.utils.PrefrencesUtils;

/**
 * Created by breland on 3/10/2015.
 */
public class BaseActivity extends Activity {

    private PrefrencesUtils prefrences;
    private BaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.prefrences = new PrefrencesUtils(this);
        Object userExtra = this.getIntent().getSerializableExtra(BaseUser.USER_EXTRA_KEY);
        if (userExtra != null){
            this.user = (BaseUser) userExtra;
        }
    }

    public PrefrencesUtils getPrefrences(){
        return this.prefrences;
    }

    public BaseUser getUser(){
        return this.user;
    }

    public void setUser(BaseUser user){
        this.user = user;
    }
}
