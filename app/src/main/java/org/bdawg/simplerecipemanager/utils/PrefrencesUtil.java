package org.bdawg.simplerecipemanager.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by brelandm on 3/6/15.
 */
public class PrefrencesUtil {

    private SharedPreferences preferences;
    private final String GUEST_MODE = "guest_mode";


    public PrefrencesUtil(Context c){
        this.preferences = PreferenceManager.getDefaultSharedPreferences(c);
    }

    public Boolean isGuestMode(){
        if (!this.preferences.contains(GUEST_MODE)){
            return null;
        }
        return this.preferences.getBoolean(GUEST_MODE, false);
    }

    public void setGuestMode(boolean value){
        this.preferences.edit().putBoolean(GUEST_MODE, value).commit();
    }
    
}
