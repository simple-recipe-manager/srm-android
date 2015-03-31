package org.bdawg.simplerecipemanager.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.authorization.api.AmazonAuthorizationManager;
import com.amazon.identity.auth.device.authorization.api.AuthorizationListener;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;
import com.amazon.identity.auth.device.shared.APIListener;
import com.amazonaws.util.ClassLoaderHelper;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;

import org.bdawg.simplerecipemanager.R;
import org.bdawg.simplerecipemanager.utils.CognitoSyncClientUtil;
import org.bdawg.simplerecipemanager.utils.ImageUtil;
import org.bdawg.simplerecipemanager.views.TransparentButton;

import java.util.concurrent.Future;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ly.whisk.model.BaseUser;

/**
 * Created by breland on 2/24/15.
 */
public class LoginActivity extends AbstractCognitoActivityWithMetrics implements Session.StatusCallback {

    private static final String TAG = LoginActivity.class.getName();
    private static final String[] AMAZON_AUTH_REALMS = {"profile", "postal_code"};
    private static final String AMAZON_KEY = "www.amazon.com";
    private static final String FACEBOOK_KEY = "graph.facebook.com";

    @InjectView(R.id.sign_in_button)
    TransparentButton signInLayout;
    @InjectView(R.id.login_background_image_view)
    ImageView loginBackground;
    @InjectView(R.id.sign_in_guest_text)
    TextView signInGuest;
    @InjectView(R.id.sign_in_holder)
    RelativeLayout signInHolder;
    @InjectView(R.id.button_amazon_signin)
    TransparentButton amazonSignIn;
    @InjectView(R.id.button_facebook_signin)
    TransparentButton facebookSignIn;
    @InjectView(R.id.button_google_signin)
    TransparentButton googleSignIn;


    private AmazonAuthorizationManager mAmazonAuthManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        getActionBar().hide();

        AsyncTask<Void, Void, Void> betterBackground = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int height = size.y;
                final Bitmap scaled = ImageUtil.decodeSampledBitmapFromResource(getResources(), R.drawable.whiskly_login_app_background_6, width, height);
                loginBackground.post(new Runnable() {
                    @Override
                    public void run() {
                        loginBackground.setImageBitmap(scaled);
                    }
                });

                return null;
            }
        };
        //betterBackground.execute();

        final long duration = 800;
        final AlphaAnimation hideAnimation = new AlphaAnimation(1.0f, 0.0f);
        final AlphaAnimation showAnimation = new AlphaAnimation(0.0f, 1.0f);
        showAnimation.setFillAfter(true);

        TranslateAnimation translateAnimation = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, -0.3f);

        final AnimationSet set = new AnimationSet(true);
        set.addAnimation(hideAnimation);
        set.addAnimation(translateAnimation);
        set.setDuration(duration);
        set.setFillAfter(true);

        signInLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInLayout.startAnimation(set);
                signInLayout.setOnClickListener(null);
                signInGuest.startAnimation(hideAnimation);
                signInGuest.setOnClickListener(null);
                signInHolder.setAlpha(0.0f);
                signInHolder.setVisibility(View.VISIBLE);
                signInHolder.animate().alpha(1f).setListener(null).setDuration(duration - 100).setStartDelay(100).start();
            }
        });
        Future<Bundle> amznGetTokenFuture = null;
        try {
            mAmazonAuthManager = new AmazonAuthorizationManager(this, Bundle.EMPTY);
            amznGetTokenFuture = mAmazonAuthManager.getToken(AMAZON_AUTH_REALMS, new AuthorizeListener());
        } catch (IllegalArgumentException badAPIKey) {
            Log.e(TAG, "Bad API key, this is weird.");
        }

        final Session session = Session.openActiveSessionFromCache(this);
        if (session != null) {
            setFacebookSession(session);
        }

        if (CognitoSyncClientUtil.hasAuthorizedLogins() || (this.getPrefrences().isGuestMode() != null && this.getPrefrences().isGuestMode().booleanValue())) {
            if (this.getPrefrences().isGuestMode() != null && this.getPrefrences().isGuestMode().booleanValue()) {
                BaseUser b = new BaseUser();
                b.setId(CognitoSyncClientUtil.getCredentialsProvider().getIdentityId());
                b.setName("Guest");
                this.setUser(b);
                if (amznGetTokenFuture != null) {
                    amznGetTokenFuture.cancel(true);
                }
            }
            launchMainActivity();
        }

        if (mAmazonAuthManager != null) {
            amazonSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAmazonAuthManager.authorize(AMAZON_AUTH_REALMS, Bundle.EMPTY,
                            new AuthorizeListener());
                }
            });
        } else {
            amazonSignIn.setEnabled(false);
        }

    }

    public void launchMainActivity() {
        final Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        final Bundle extras = new Bundle();
        if (this.getUser().getId() == null || this.getUser().getName() == null) {
            return;
        }
        extras.setClassLoader(BaseUser.class.getClassLoader());
        extras.putSerializable(BaseUser.USER_EXTRA_KEY, this.getUser());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LoginActivity.this.startActivity(mainIntent, extras);
                finish();
            }
        });


    }

    @OnClick({R.id.sign_in_guest_text})
    public void signInAsGuestClicked() {
        this.getPrefrences().setGuestMode(true);
        BaseUser b = new BaseUser();
        b.setId(CognitoSyncClientUtil.getCredentialsProvider().getIdentityId());
        b.setName("Guest");
        this.setUser(b);
        launchMainActivity();
    }

    @OnClick({R.id.button_facebook_signin})
    public void singInFacebookClicked() {
        Session.openActiveSession(LoginActivity.this, true,
                LoginActivity.this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(this, requestCode,
                resultCode, data);
    }

    @Override
    public void call(Session session, SessionState state, Exception exception) {
        if (session.isOpened()) {
            setFacebookSession(session);
        }
    }

    private void setFacebookSession(Session session) {
        CognitoSyncClientUtil.addLogins(FACEBOOK_KEY,
                session.getAccessToken());
        facebookSignIn.setVisibility(View.GONE);
        AsyncTask<Void, Void, Void> getIdTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                LoginActivity.this.getUser().setId(CognitoSyncClientUtil.getCredentialsProvider().getIdentityId());
                launchMainActivity();
                return null;
            }
        };
        getIdTask.execute();

        Request.newMeRequest(session, new Request.GraphUserCallback() {
            @Override
            public void onCompleted(GraphUser graphUser, Response response) {
                LoginActivity.this.getUser().setName(graphUser.getName());
                launchMainActivity();
            }
        }).executeAsync();
        facebookSignIn.setEnabled(false);

    }

    private class AuthorizeListener implements AuthorizationListener {

        /* Authorization was completed successfully. */
        @Override
        public void onSuccess(Bundle response) {
            Log.i(TAG, "Auth successful. Start to getToken");
            mAmazonAuthManager.getToken(AMAZON_AUTH_REALMS, new AuthTokenListener());
            mAmazonAuthManager.getProfile(new APIListener() {
                @Override
                public void onSuccess(Bundle response) {
                    Bundle profileBundle = response
                            .getBundle(AuthzConstants.BUNDLE_KEY.PROFILE.val);
                    if (profileBundle != null) {
                        final String name = profileBundle
                                .getString(AuthzConstants.PROFILE_KEY.NAME.val);
                        LoginActivity.this.getUser().setName(name);
                    }
                }

                @Override
                public void onError(AuthError ae) {
                    Log.e(TAG, "AuthError during getProfile", ae);
                }
            });
        }

        /* There was an error during the attempt to authorize the application. */
        @Override
        public void onError(AuthError ae) {
            Log.e(TAG, "AuthError during authorization", ae);
        }

        /* Authorization was cancelled before it could be completed. */
        @Override
        public void onCancel(Bundle cause) {
            Log.e(TAG, "User cancelled authorization");
        }
    }

    private class AuthTokenListener implements APIListener {

        @Override
        public void onSuccess(Bundle response) {
            final String token = response
                    .getString(AuthzConstants.BUNDLE_KEY.TOKEN.val);
            if (token == null) {
                return;
            }
            CognitoSyncClientUtil.addLogins(AMAZON_KEY, token);
            LoginActivity.this.getUser().setId(CognitoSyncClientUtil.getCredentialsProvider().getIdentityId());
            launchMainActivity();

        }

        @Override
        public void onError(AuthError ae) {
            Log.e(TAG, "Failed to get token", ae);
        }
    }


}
