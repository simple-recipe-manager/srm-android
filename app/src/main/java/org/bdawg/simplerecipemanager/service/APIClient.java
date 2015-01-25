package org.bdawg.simplerecipemanager.service;

import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    public Recipe getRecipeForId(String id) throws IOException {
        StringBuilder base = buildBase();
        base.append(recipeEndpoint);
        String fullURL = base.append(String.format("/%s", id)).toString();
        HttpClient http = new DefaultHttpClient();
        HttpUriRequest request = new HttpGet(fullURL);
        HttpResponse response = http.execute(request);
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
            throw new IOException("Server did not return OK");
        }
        String responseString = convertStreamToString(response.getEntity().getContent(), Charset.forName("UTF-8"));
        Log.d(TAG, responseString);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Recipe toReturn = mapper.readValue(responseString, Recipe.class);
        return toReturn;

    }

    static String convertStreamToString(java.io.InputStream is, Charset charset) {
        java.util.Scanner s = new java.util.Scanner(is, charset.name()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public Recipe getRecipeForId(UUID id) throws IOException {
        return this.getRecipeForId(id.toString());
    }
}
