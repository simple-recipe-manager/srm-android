package org.bdawg.simplerecipemanager;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import org.bdawg.simplerecipemanager.domain.IngredientAndAmount;
import org.bdawg.simplerecipemanager.domain.Recipe;
import org.bdawg.simplerecipemanager.domain.Step;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;

/**
 * Created by breland on 1/4/2015.
 */
public class RecipeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getActionBar().hide();

        View recipeView = inflater.inflate(R.layout.fragment_recipe, container, false);

        Bundle args = getArguments();
        Recipe r = (Recipe) args.getSerializable("recipe");

        TextView titleView = (TextView) recipeView.findViewById(R.id.recipe_frag_title);
        TextView addedAt = (TextView) recipeView.findViewById(R.id.recipe_frag_added_at);

        titleView.setText(r.getRecipe_name());
        DateFormat df = DateFormat.getDateInstance();
        addedAt.setText(String.format(this.getString(R.string.added_text_format), df.format(new Date(r.getAddedAt()))));


        ImageView defaultImageView = (ImageView) recipeView.findViewById(R.id.recipe_frag_cv_header_image);

        Animation fadeInAnimation = new AlphaAnimation(0, 100);
        fadeInAnimation.setDuration(200);

        Ion.with(defaultImageView)
                .animateIn(fadeInAnimation)
                .load(r.getDefaultImageURL().toString());

        TextView directionsText = (TextView) recipeView.findViewById(R.id.recipe_frag_directions_text);

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
            orderedDirectionSteps.append(step + ". " + s.getStepDetails() + "\n");
            step++;
        }
        directionsText.setText(orderedDirectionSteps.toString());

        LinearLayout ingredientChecklist = (LinearLayout) recipeView.findViewById(R.id.recipe_frag_ingr_check_list);
        if (r.getIngredients().size() == 1) {
            Set<IngredientAndAmount> ingrsAndAmounts = r.getIngredients().get(r.getIngredients().keySet().iterator().next());
            for (IngredientAndAmount ia : ingrsAndAmounts) {
                String formattedIngr = String.format("%.2f %s %s", ia.getValue(), ia.getUnit().getTag(), ia.getIngredient().getName());
                IngredientView ingrView = new IngredientView(getActivity(), null);
                ingrView.setText(formattedIngr);
                ingredientChecklist.addView(ingrView);
            }
        } else {
            for (String yieldId : r.getIngredients().keySet()) {

            }
        }


        return recipeView;
    }
}
