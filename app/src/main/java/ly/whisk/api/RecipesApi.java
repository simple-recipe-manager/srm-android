package ly.whisk.api;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;

import com.koushikdutta.ion.Ion;

import ly.whisk.api.client.ApiException;

import ly.whisk.model.Recipe;


import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class RecipesApi {
  String basePath = "https://api.whisk.ly/v1";
    private Context context;
    public RecipesApi(Context context){
        this.context = context;
    }


  public void setBasePath(String basePath) {
    this.basePath = basePath;
  }

  public String getBasePath() {
    return basePath;
  }

  
    
  public List<Recipe> recipesGet (Integer pageNum, Integer pageSize, String search) throws ApiException, InterruptedException, ExecutionException {
    Object postBody = null;
    

    // create path and map variables
    String path = "/recipes".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, String> formParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(pageNum)))
      queryParams.put("pageNum", String.valueOf(pageNum));
    if(!"null".equals(String.valueOf(pageSize)))
      queryParams.put("pageSize", String.valueOf(pageSize));
    if(!"null".equals(String.valueOf(search)))
      queryParams.put("search", String.valueOf(search));
    

    

    String[] contentTypes = {
      
    };

    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

return null;
  }
  
    
  public Recipe recipesPost (Recipe Recipe) throws ApiException, InterruptedException, ExecutionException {
    Object postBody = null;
    

    // create path and map variables
    String path = "/recipes".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, String> formParams = new HashMap<String, String>();

      String[] contentTypes = {

      };
      String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

      return null;

  }
  
    
  public Recipe recipesGet (String id, Fragment thisContext) throws InterruptedException, ExecutionException {
    Object postBody = null;
    

    // create path and map variables
    String path = "/recipes/{id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "id" + "\\}", id.toString());

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, String> formParams = new HashMap<String, String>();

    

    

    String[] contentTypes = {
      
    };

    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";


      com.koushikdutta.ion.Response<Recipe> ioResponse = Ion.with(thisContext).load("GET", "https://api.whisk.ly/v1/recipes/5fe8eb02-a05b-401c-91f0-7f8a4e6b984d").setLogging("ION", Log.VERBOSE).as(Recipe.class).withResponse().get();
        return ioResponse.getResult();
  }
  
}
