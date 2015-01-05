package org.bdawg.simplerecipemanager;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.bdawg.simplerecipemanager.domain.Recipe;

/**
 * Created by breland on 1/4/2015.
 */
public class RecipeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View recipeView = inflater.inflate(R.layout.fragment_recipe, container, false);

        Bundle args = getArguments();
        Recipe r = (Recipe)args.getSerializable("recipe");

        TextView titleTextView = (TextView)recipeView.findViewById(R.id.recipe_view_title);
        titleTextView.setText(r.getRecipe_name());



        return recipeView;
    }
}
