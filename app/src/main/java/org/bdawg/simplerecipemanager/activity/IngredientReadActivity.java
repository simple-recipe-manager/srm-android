package org.bdawg.simplerecipemanager.activity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import org.bdawg.simplerecipemanager.utils.RationalUtil;
import org.bdawg.simplerecipemanager.utils.TagUtil;

import java.util.Collection;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import ly.whisk.model.IngredientAndAmount;
import ly.whisk.model.Recipe;

/**
 * Created by breland on 1/31/2015.
 */
public class IngredientReadActivity extends AbstractCognitoActivityWithMetrics implements TextToSpeech.OnInitListener {

    private Recipe recipe;
    private TextToSpeech ttsEngine;
    private Boolean ready = false;
    private BlockingQueue<String> thingsToSpeak;
    private Thread speakerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = this.getIntent().getExtras();
        recipe = (Recipe) args.getSerializable("recipe");
        this.thingsToSpeak = new ArrayBlockingQueue<String>(100);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ttsEngine = new TextToSpeech(this, this);
        this.speak(recipe.getRecipe_name());
        if (recipe.getIngredients().size() == 1) {
            Collection<IngredientAndAmount> ingrsAndAmounts = recipe.getIngredients();
            for (IngredientAndAmount ia : ingrsAndAmounts) {
                StringBuilder amount = new StringBuilder();
                int wholeAmount = (int) ia.getValue().intValue();
                if (wholeAmount != ia.getValue()) {
                    //format
                    amount.append(wholeAmount);
                    amount.append(String.format(" %s", RationalUtil.toRational(ia.getValue() - wholeAmount)));
                } else {
                    amount.append(wholeAmount);
                }
                String formattedIngr = String.format("%s %s %s", amount.toString(), TagUtil.getAppropriateTag(ia), ia.getIngredient().getName());
                this.speak(formattedIngr);
            }
        } else {
            //hmm
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.speakerThread.interrupt();
        this.ttsEngine.shutdown();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            ttsEngine.setLanguage(Locale.getDefault());
            ready = true;
            speakerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!Thread.currentThread().isInterrupted()) {
                        String toSpeakNext = thingsToSpeak.poll();
                        ttsEngine.speak(toSpeakNext, TextToSpeech.QUEUE_ADD, null, "test");
                    }
                }
            });
            speakerThread.start();
        } else {
            ready = false;
        }
    }

    public void speak(final String text) {
        this.thingsToSpeak.add(text);
    }
}
