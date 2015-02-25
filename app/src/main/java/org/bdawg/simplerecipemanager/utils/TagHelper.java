package org.bdawg.simplerecipemanager.utils;

import ly.whisk.model.IngredientAndAmount;

/**
 * Created by breland on 2/15/15.
 */
public class TagHelper {
    private TagHelper(){

    }
    public static String getAppropriateTag(IngredientAndAmount amt) {
        if (amt.getValue() > 1 && amt.getUnit().getMultipleTag() != null) {
            return amt.getUnit().getMultipleTag();
        } else {
            return amt.getUnit().getTag();
        }
    }
}
