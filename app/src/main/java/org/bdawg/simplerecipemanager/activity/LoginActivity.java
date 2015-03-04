package org.bdawg.simplerecipemanager.activity;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
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
import com.koushikdutta.ion.Ion;

import org.bdawg.simplerecipemanager.R;
import org.bdawg.simplerecipemanager.utils.ImageUtils;
import org.bdawg.simplerecipemanager.views.TransparentButton;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by breland on 2/24/15.
 */
public class LoginActivity extends Activity {
    @InjectView(R.id.sign_in_button)
    TransparentButton signInLayout;
    @InjectView(R.id.login_background_image_view)
    ImageView loginBackground;
    @InjectView(R.id.sign_in_guest_text)
    TextView signInGuest;
    @InjectView(R.id.sign_in_holder) RelativeLayout signInHolder;


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
                signInHolder.animate().alpha(1f).setListener(null).setDuration(duration-100).setStartDelay(100).start();
            }
        });
//        mAmazonAuthManager = new AmazonAuthorizationManager(this, Bundle.EMPTY);
/*
        loginWithAmazonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAmazonAuthManager.authorize(
                        new String []{"profile","postal_code"},
                        Bundle.EMPTY, new AuthorizeListener());
            }
        });
        */
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

    private String uriForResrouceId(int resId){
        Resources resources = this.getResources();
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + resources.getResourcePackageName(resId) + '/' + resources.getResourceTypeName(resId) + '/' + resources.getResourceEntryName(resId)).toString();
    }
}
