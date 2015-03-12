package org.bdawg.simplerecipemanager.activity;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.authorization.api.AmazonAuthorizationManager;
import com.amazon.identity.auth.device.authorization.api.AuthorizationListener;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;
import com.amazon.identity.auth.device.shared.APIListener;
import com.koushikdutta.ion.Ion;

import org.bdawg.simplerecipemanager.R;
import org.bdawg.simplerecipemanager.models.BaseUser;
import org.bdawg.simplerecipemanager.utils.ImageUtils;
import org.bdawg.simplerecipemanager.views.TransparentButton;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by breland on 2/24/15.
 */
public class LoginActivity extends AbstractMetricsActivity implements AuthorizationListener, APIListener{

    private static final String TAG = LoginActivity.class.getName();
    private static final String[] AMAZON_AUTH_REALMS = {"profile", "postal_code"};

    @InjectView(R.id.sign_in_button) TransparentButton signInLayout;
    @InjectView(R.id.login_background_image_view) ImageView loginBackground;
    @InjectView(R.id.sign_in_guest_text) TextView signInGuest;
    @InjectView(R.id.sign_in_holder) RelativeLayout signInHolder;
    @InjectView(R.id.button_amazon_signin) TransparentButton amazonSignIn;
    @InjectView(R.id.button_facebook_signin) TransparentButton facebookSignIn;
    @InjectView(R.id.button_google_signin) TransparentButton googleSignIn;


    private AmazonAuthorizationManager mAmazonAuthManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.cognitoIsAuthorized() || (this.getPrefrences().isGuestMode() != null && this.getPrefrences().isGuestMode())){
            if (this.getPrefrences().isGuestMode() != null && this.getPrefrences().isGuestMode().booleanValue()){
                BaseUser b = new BaseUser();
                b.setId(this.getCognitoProvider().getIdentityId());
                this.setUser(b);
            }
            launchMainActivity();
        }
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
                signInHolder.animate().alpha(1f).setListener(null).setDuration(duration-100).setStartDelay(100).start();
            }
        });
      try {
          mAmazonAuthManager = new AmazonAuthorizationManager(this, Bundle.EMPTY);
          mAmazonAuthManager.getProfile(this);
      } catch (IllegalArgumentException badAPIKey){
          Log.e(TAG, "Bad API key, this is weird.");
      }

        if (mAmazonAuthManager != null) {
            amazonSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAmazonAuthManager.authorize(
                            AMAZON_AUTH_REALMS,
                            Bundle.EMPTY, LoginActivity.this);
                }
            });
        } else {
            amazonSignIn.setEnabled(false);
        }

    }

    private boolean cognitoIsAuthorized(){
        return this.getCognitoProvider().getLogins() != null && this.getCognitoProvider().getLogins().size() > 0;
    }

    @Override
    public void onCancel(Bundle bundle) {


    }

    @OnClick({R.id.sign_in_guest_text})
    public void signInAsGuestClicked(){
        this.getPrefrences().setGuestMode(true);
        BaseUser b = new BaseUser();
        b.setId(this.getCognitoProvider().getIdentityId());
        this.setUser(b);
    }

    @Override
    public void onSuccess(Bundle bundle) {
        Future<Bundle> profileBundleFuture = this.mAmazonAuthManager.getProfile(null);
        String token = bundle.getString(AuthzConstants.BUNDLE_KEY.TOKEN.val);
        for (String s : bundle.keySet()){
            Log.d(TAG, s);
        }
        Bundle profileBundle = null;
        try {
            profileBundle = profileBundleFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        String name = profileBundle.getString(AuthzConstants.PROFILE_KEY.NAME.val);
        String postal = profileBundle.getString(AuthzConstants.PROFILE_KEY.POSTAL_CODE.val);
        Map<String, String> logins = LoginActivity.this.getCognitoProvider().getLogins();
        if (logins == null){
            logins = new HashMap<String, String>();
        }
        logins.put("www.amazon.com", token);
        LoginActivity.this.addCognitoCredentials(logins);
        this.getUser().setName(name);
        this.getUser().setPostalCode(postal);
        if (cognitoIsAuthorized()){
            launchMainActivity();
        }
    }

    public void launchMainActivity(){
        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        Bundle extras = new Bundle();
        extras.putSerializable(BaseUser.USER_EXTRA_KEY, this.getUser());
        LoginActivity.this.startActivity(mainIntent,extras);
    }

    @Override
    public void onError(AuthError authError) {

    }

    private String uriForResrouceId(int resId){
        Resources resources = this.getResources();
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + resources.getResourcePackageName(resId) + '/' + resources.getResourceTypeName(resId) + '/' + resources.getResourceEntryName(resId)).toString();
    }
}
