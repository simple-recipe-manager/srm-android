package org.bdawg.simplerecipemanager.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import org.bdawg.simplerecipemanager.R;
import org.bdawg.simplerecipemanager.activity.IngredientReadActivityWithMetrics;
import org.bdawg.simplerecipemanager.utils.Rational;
import org.bdawg.simplerecipemanager.utils.TagHelper;
import org.bdawg.simplerecipemanager.views.IngredientView;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ly.whisk.model.IngredientAndAmount;
import ly.whisk.model.Recipe;
import ly.whisk.model.Step;

/**
 * Created by breland on 1/4/2015.
 */
public class RecipeFragment extends Fragment {

    @InjectView(R.id.recipe_frag_btn_read_ingrs)
    Button readAsTTSButton;
    @InjectView(R.id.recipe_frag_title)
    TextView titleView;
    @InjectView(R.id.recipe_frag_added_at)
    TextView addedAt;
    @InjectView(R.id.recipe_frag_cv_header_image)
    ImageView defaultImageView;
    @InjectView(R.id.recipe_frag_directions_text)
    TextView directionsText;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getActivity().getActionBar().hide();

        View recipeView = inflater.inflate(R.layout.fragment_recipe, container, false);
        ButterKnife.inject(this, recipeView);

        Bundle args = getArguments();
        Recipe r = (Recipe) args.getSerializable("recipe");

        readAsTTSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recipeTTSIntent = new Intent(RecipeFragment.this.getActivity(), IngredientReadActivityWithMetrics.class);
                recipeTTSIntent.putExtras(getArguments());
                RecipeFragment.this.startActivity(recipeTTSIntent);
            }
        });


        titleView.setText(r.getRecipe_name());
        DateFormat df = DateFormat.getDateInstance();
        addedAt.setText(String.format(this.getString(R.string.added_text_format), df.format(new Date(r.getAdded_at()))));


        Animation fadeInAnimation = new AlphaAnimation(0, 100);
        fadeInAnimation.setDuration(200);

        Ion.with(defaultImageView)
                .animateIn(fadeInAnimation)
                .load(r.getDefault_image_url());


        Step[] steps = r.getSteps().toArray(new Step[0]);
        Arrays.sort(steps, new Comparator<Step>() {
            @Override
            public int compare(Step lhs, Step rhs) {
                return Integer.compare(lhs.getOrder(), rhs.getOrder());
            }
        });
        StringBuilder orderedDirectionSteps = new StringBuilder();
        int step = 1;
        for (Step s : steps) {
            orderedDirectionSteps.append(step + ". " + s.getStep_details() + "\n");
            step++;
        }
        directionsText.setText(orderedDirectionSteps.toString());

        LinearLayout ingredientChecklist = (LinearLayout) recipeView.findViewById(R.id.recipe_frag_ingr_check_list);
        if (r.getIngredients().size() == 1) {
            Collection<IngredientAndAmount> ingrsAndAmounts = r.getIngredients();
            for (IngredientAndAmount ia : ingrsAndAmounts) {
                StringBuilder amount = new StringBuilder();
                int wholeAmount = (int) ia.getValue().intValue();
                if (wholeAmount != ia.getValue().doubleValue()) {
                    //format
                    amount.append(wholeAmount);
                    amount.append(String.format(" %s", Rational.toRational(ia.getValue() - wholeAmount)));
                } else {
                    amount.append(wholeAmount);
                }
                String formattedIngr = String.format("%s %s %s", amount.toString(), TagHelper.getAppropriateTag(ia), ia.getIngredient().getName());
                IngredientView ingrView = new IngredientView(getActivity(), null);
                ingrView.setText(formattedIngr);
                ingredientChecklist.addView(ingrView);
            }
        } else {
            for (IngredientAndAmount ia : r.getIngredients()) {

            }
        }


        return recipeView;
    }
}
