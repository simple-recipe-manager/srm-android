package org.bdawg.simplerecipemanager.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
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

import org.bdawg.simplerecipemanager.R;
import org.bdawg.simplerecipemanager.utils.ImageUtils;
import org.bdawg.simplerecipemanager.views.TransparentButton;
import org.parceler.Parcels;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ly.whisk.model.BaseUser;

/**
 * Created by breland on 2/24/15.
 */
public class LoginActivityWithMetrics extends AbstractCognitoActivityWithMetrics implements AuthorizationListener, APIListener {

    private static final String TAG = LoginActivityWithMetrics.class.getName();
    private static final String[] AMAZON_AUTH_REALMS = {"profile", "postal_code"};
    private static final String AMAZON_KEY = "www.amazon.com";

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

    private boolean hasRequestedAmznProfile = false;
    private boolean hasRequestedAmznToken = true;
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
                final Bitmap scaled = ImageUtils.decodeSampledBitmapFromResource(getResources(), R.drawable.whiskly_login_app_background_6, width, height);
                loginBackground.post(new Runnable() {
                    @Override
                    public void run() {
                        loginBackground.setImageBitmap(scaled);
                    }
                });

                return null;
            }
        };
        betterBackground.execute();

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
            amznGetTokenFuture = mAmazonAuthManager.getToken(AMAZON_AUTH_REALMS, this);
        } catch (IllegalArgumentException badAPIKey) {
            Log.e(TAG, "Bad API key, this is weird.");
        }

        if (this.cognitoIsAuthorizedWithLogins() || (this.getPrefrences().isGuestMode() != null && this.getPrefrences().isGuestMode())) {
            if (this.getPrefrences().isGuestMode() != null && this.getPrefrences().isGuestMode().booleanValue()) {
                BaseUser b = new BaseUser();
                b.setId(this.getCognitoProvider().getIdentityId());
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
                    mAmazonAuthManager.authorize(
                            AMAZON_AUTH_REALMS,
                            Bundle.EMPTY, LoginActivityWithMetrics.this);
                }
            });
        } else {
            amazonSignIn.setEnabled(false);
        }

    }

    public void launchMainActivity() {
        Intent mainIntent = new Intent(LoginActivityWithMetrics.this, MainActivityWithMetrics.class);
        Bundle extras = new Bundle();
        extras.putParcelable(BaseUser.USER_EXTRA_KEY, Parcels.wrap(this.getUser()));
        finish();
        LoginActivityWithMetrics.this.startActivity(mainIntent, extras);

    }

    @OnClick({R.id.sign_in_guest_text})
    public void signInAsGuestClicked() {
        this.getPrefrences().setGuestMode(true);
        BaseUser b = new BaseUser();
        b.setId(this.getCognitoProvider().getIdentityId());
        this.setUser(b);
        launchMainActivity();
    }

    @Override
    public void onCancel(Bundle bundle) {

    }

    @Override
    public void onSuccess(Bundle bundle) {
        if (!bundle.containsKey(AuthzConstants.BUNDLE_KEY.PROFILE.val) && this.getUser().getName() == null && !this.hasRequestedAmznProfile) {
            this.mAmazonAuthManager.getProfile(this);
            this.hasRequestedAmznProfile = true;
        } else {
            Bundle profileBundle = bundle.getBundle(AuthzConstants.BUNDLE_KEY.PROFILE.val);
            if (profileBundle != null) {
                String name = profileBundle.getString(AuthzConstants.PROFILE_KEY.NAME.val);
                String postal = profileBundle.getString(AuthzConstants.PROFILE_KEY.POSTAL_CODE.val);
                this.getUser().setName(name);
                this.getUser().setPostalCode(postal);
            }
        }
        String token = bundle.getString(AuthzConstants.BUNDLE_KEY.TOKEN.val);
        if (token != null && !this.getCognitoProvider().getLogins().containsKey(AMAZON_KEY)) {
            Map<String, String> logins = LoginActivityWithMetrics.this.getCognitoProvider().getLogins();
            if (logins == null) {
                logins = new HashMap<String, String>();
            }
            logins.put(AMAZON_KEY, token);
            LoginActivityWithMetrics.this.addCognitoCredentials(logins);
            this.getUser().setId(this.getCognitoProvider().getIdentityId());

        } else if (this.getUser().getId() == null) {
            this.getUser().setId(this.getCognitoProvider().getIdentityId());

        } else if (!this.hasRequestedAmznToken) {
            this.hasRequestedAmznToken = true;
            this.mAmazonAuthManager.getToken(AMAZON_AUTH_REALMS, this);
        }

        if (this.getUser().getId() != null && this.getUser().getName() != null && cognitoIsAuthorizedWithLogins()) {
            launchMainActivity();
        }
    }

    @Override
    public void onError(AuthError authError) {
        Log.e(TAG, "Failed to do some action for login.", authError);
    }
}
