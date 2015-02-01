package org.bdawg.simplerecipemanager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsConfig;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.InitializationException;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.MobileAnalyticsManager;
import com.amazonaws.regions.Regions;

/**
 * Created by breland on 1/31/2015.
 */
public abstract class AbstractMetricsActivity extends Activity {

    private static final String TAG = AbstractMetricsActivity.class.getSimpleName();
    private static MobileAnalyticsManager analytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CognitoCachingCredentialsProvider cognitoProvider = new CognitoCachingCredentialsProvider(
                this.getApplicationContext(),
                "224842466274",
                "us-east-1:866d80cc-24e2-4c83-b6b4-f1c722310e23",
                "arn:aws:iam::224842466274:role/Cognito_SimpleRecipeManagerUnauth_DefaultRole",
                "arn:aws:iam::224842466274:role/Cognito_SimpleRecipeManagerAuth_DefaultRole",
                Regions.US_EAST_1
        );

        try {
            AnalyticsConfig config = new AnalyticsConfig();
            config.withAllowsWANDelivery(true);
            analytics = MobileAnalyticsManager.getOrCreateInstance(
                    this.getApplicationContext(),
                    "5aeda332c6a7454fa411641a6a82a8e8", //Mobile Analytics App ID
                    Regions.US_EAST_1,
                    cognitoProvider,
                    config);
        } catch (InitializationException ex)         {
            Log.e(TAG, "Failed to setup cognito/analytics", ex);
        }
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
