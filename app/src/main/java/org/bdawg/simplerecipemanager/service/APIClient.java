package org.bdawg.simplerecipemanager.service;

import java.util.UUID;

/**
 * Created by breland on 1/4/2015.
 */
public class APIClient {
    public Recipe getRecipeForId(String Id){

    }

    public Recipe getRecipeForId(UUID id){
        return this.getRecipeForId(id.toString());
    }
}
