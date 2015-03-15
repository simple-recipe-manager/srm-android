package org.bdawg.simplerecipemanager.activity;

import android.os.Bundle;
import android.util.Log;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.auth.CognitoCredentialsProvider;
import com.amazonaws.auth.IdentityChangedListener;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsConfig;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.InitializationException;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.MobileAnalyticsManager;
import com.amazonaws.regions.Regions;


import java.util.Map;

import ly.whisk.model.BaseUser;

/**
 * Created by breland on 1/31/2015.
 */
public abstract class AbstractCognitoActivityWithMetrics extends BaseActivity implements IdentityChangedListener {

    private static final String TAG = AbstractCognitoActivityWithMetrics.class.getSimpleName();
    private static MobileAnalyticsManager analytics;

    private CognitoCredentialsProvider cognitoProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cognitoProvider = new CognitoCachingCredentialsProvider(
                this.getApplicationContext(),
                "us-east-1:866d80cc-24e2-4c83-b6b4-f1c722310e23",
                Regions.US_EAST_1
        );
        cognitoProvider.registerIdentityChangedListener(this);

        try {
            AnalyticsConfig config = new AnalyticsConfig();
            config.withAllowsWANDelivery(true);
            analytics = MobileAnalyticsManager.getOrCreateInstance(
                    this.getApplicationContext(),
                    "5aeda332c6a7454fa411641a6a82a8e8", //Mobile Analytics App ID
                    Regions.US_EAST_1,
                    cognitoProvider,
                    config);
        } catch (InitializationException ex) {
            Log.e(TAG, "Failed to setup cognito/analytics", ex);
        }
    }

    //TODO: This
    @Override
    public void identityChanged(String oldIdentity, String newIdentity) {

    }

    public CognitoCredentialsProvider getCognitoProvider() {
        return this.cognitoProvider;
    }

    public void addCognitoCredentials(Map<String, String> tokens) {
        this.cognitoProvider.setLogins(tokens);
        this.cognitoProvider.refresh();
        if (this.getUser() == null) {
            BaseUser newUser = new BaseUser();
            this.setUser(newUser);
        }
        this.getUser().setId(this.cognitoProvider.getIdentityId());
    }

    public boolean cognitoIsAuthorizedWithLogins() {
        return this.getCognitoProvider().getLogins() != null && this.getCognitoProvider().getLogins().size() > 0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (analytics != null) {
            analytics.getSessionClient().pauseSession();
            analytics.getEventClient().submitEvents();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (analytics != null) {
            analytics.getSessionClient().resumeSession();
        }
    }
}
