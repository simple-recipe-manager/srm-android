package org.bdawg.simplerecipemanager.service;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.bdawg.simplerecipemanager.domain.Recipe;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * Created by breland on 1/4/2015.
 */
public class APIClient {

    private static final String TAG = APIClient.class.getName();

    private String host = "api.simplerecipemanager.com";
    private String protocol = "http";

    private String recipeEndpoint = "recipes";

    private StringBuilder buildBase() {
        return new StringBuilder(String.format("%s://%s/", protocol, host));
    }

    public Future<Recipe> getRecipeForId(String id, Context context, FutureCallback<Recipe> callback) throws IOException {

        StringBuilder base = buildBase();
        base.append(recipeEndpoint);
        String fullURL = base.append(String.format("/%s", id)).toString();
        return Ion.with(context)
                .load(fullURL)
                .as(Recipe.class)
                .setCallback(callback);
    }

    static String convertStreamToString(java.io.InputStream is, Charset charset) {
        java.util.Scanner s = new java.util.Scanner(is, charset.name()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public Future<Recipe> getRecipeForId(UUID id, Context context, FutureCallback<Recipe> callback) throws IOException {
        return this.getRecipeForId(id.toString(), context, callback);
    }

    public Recipe getRecipeForId(String id, Context context) throws IOException, ExecutionException, InterruptedException {
        return getRecipeForId(id, context, null).get();
    }
}
