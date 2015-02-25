package org.bdawg.simplerecipemanager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.authorization.api.AmazonAuthorizationManager;
import com.amazon.identity.auth.device.authorization.api.AuthorizationListener;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;

import org.bdawg.simplerecipemanager.R;

import butterknife.InjectView;

/**
 * Created by breland on 2/24/15.
 */
public class LoginActivity extends Activity {
    @InjectView(R.id.login_withAmazonButton) Button loginWithAmazonButton;


    private AmazonAuthorizationManager mAmazonAuthManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAmazonAuthManager = new AmazonAuthorizationManager(this, Bundle.EMPTY);

        loginWithAmazonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAmazonAuthManager.authorize(
                        new String []{"profile","postal_code"},
                        Bundle.EMPTY, new AuthorizeListener());
            }
        });
    }

    private class AuthorizeListener implements AuthorizationListener {

        /* Authorization was completed successfully. */
        @Override
        public void onSuccess(Bundle response) {
        }
        /* There was an error during the attempt to authorize the application. */
        @Override
        public void onError(AuthError ae) {
        }
        /* Authorization was cancelled before it could be completed. */
        @Override
        public void onCancel(Bundle cause) {
        }
    }
}
