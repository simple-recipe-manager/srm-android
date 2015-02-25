package ly.whisk.api;


import android.content.Context;

import ly.whisk.api.client.ApiException;
import ly.whisk.api.client.ApiInvoker;

import ly.whisk.model.Recipe;


import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class RecipesApi {
  String basePath = "https://api.whisk.ly/v1";
  ApiInvoker apiInvoker = ApiInvoker.getInstance();

  public ApiInvoker getInvoker() {
    return apiInvoker;
  }

  public void setBasePath(String basePath) {
    this.basePath = basePath;
  }

  public String getBasePath() {
    return basePath;
  }

  
    
  public List<Recipe> recipesGet (Integer pageNum, Integer pageSize, String search, Context context) throws ApiException, InterruptedException, ExecutionException {
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

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, context);
      if(response != null){
        return (List<Recipe>) ApiInvoker.deserialize(response, "Recipe", Recipe.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      if(ex.getCode() == 404) {
      	return  null;
      }
      else {
        throw ex;
      }
    }
  }
  
    
  public Recipe recipesPost (Recipe Recipe, Context context) throws ApiException, InterruptedException, ExecutionException {
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

      try {
      String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, postBody, headerParams, formParams, contentType, context);
      if(response != null){
        return (Recipe) ApiInvoker.deserialize(response, "", Recipe.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      if(ex.getCode() == 404) {
      	return  null;
      }
      else {
        throw ex;
      }
    }
  }
  
    
  public Recipe recipesGet (String id, Context context) throws ApiException, InterruptedException, ExecutionException {
    Object postBody = null;
    

    // create path and map variables
    String path = "/recipes/{id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, String> formParams = new HashMap<String, String>();

    

    

    String[] contentTypes = {
      
    };

    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";


    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, context);
      if(response != null){
        return (Recipe) ApiInvoker.deserialize(response, "", Recipe.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      if(ex.getCode() == 404) {
      	return  null;
      }
      else {
        throw ex;
      }
    }
  }
  
}
