package org.bdawg.simplerecipemanager;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import org.bdawg.simplerecipemanager.domain.Recipe;

import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by breland on 1/31/2015.
 */
public class IngredientReadActivity extends Activity implements TextToSpeech.OnInitListener {

    private Recipe recipe;
    private TextToSpeech ttsEngine;
    private Boolean ready = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = this.getIntent().getExtras();
        recipe = (Recipe) args.getSerializable("recipe");
        ttsEngine = new TextToSpeech(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.speak(recipe.getRecipe_name());
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            ttsEngine.setLanguage(Locale.getDefault());
            ready = true;
        } else {
            ready = false;
        }
        synchronized (ready) {
            ready.notify();
        }
    }

    public void speak(final String text) {
        if (!ready) {
            try {
                synchronized (ready) {
                    ready.wait(1500);
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        if (ready) {
            final HashMap<String, String> hash = new HashMap<String, String>();
            hash.put(TextToSpeech.Engine.KEY_PARAM_STREAM,
                    String.valueOf(AudioManager.STREAM_NOTIFICATION));
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ttsEngine.speak(text, TextToSpeech.QUEUE_ADD, hash);

                }
            });

        }
    }
}
