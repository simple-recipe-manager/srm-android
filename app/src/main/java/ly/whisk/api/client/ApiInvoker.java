package ly.whisk.api.client;

import android.content.Context;

import com.fasterxml.jackson.databind.*;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.Builders;
import com.koushikdutta.ion.builder.LoadBuilder;


import java.io.BufferedInputStream;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class ApiInvoker {
  private static ApiInvoker INSTANCE = new ApiInvoker();
  private Map<String, String> defaultHeaderMap = new HashMap<String, String>();
  private boolean isDebug = false;

  public void enableDebug() {
    isDebug = true;
  }

  public static ApiInvoker getInstance() {
    return INSTANCE;
  }

  public void addDefaultHeader(String key, String value) {
     defaultHeaderMap.put(key, value);
  }

  public String escapeString(String str) {
    try{
      return URLEncoder.encode(str, "utf8").replaceAll("\\+", "%20");
    }
    catch(UnsupportedEncodingException e) {
      return str;
    }
  }

  public static Object deserialize(String json, String containerType, Class cls) throws ApiException {
    try{
      if("List".equals(containerType)) {
        JavaType typeInfo = JsonUtil.getJsonMapper().getTypeFactory().constructCollectionType(List.class, cls);
        List response = (List<?>) JsonUtil.getJsonMapper().readValue(json, typeInfo);
        return response;
      }
      else if(String.class.equals(cls)) {
        if(json != null && json.startsWith("\"") && json.endsWith("\"") && json.length() > 1)
          return json.substring(1, json.length() - 2);
        else
          return json;
      }
      else {
        return JsonUtil.getJsonMapper().readValue(json, cls);
      }
    }
    catch (IOException e) {
      throw new ApiException(500, e.getMessage());
    }
  }

  public static String serialize(Object obj) throws ApiException {
    try {
      if (obj != null)
        return JsonUtil.getJsonMapper().writeValueAsString(obj);
      else
        return null;
    }
    catch (Exception e) {
      throw new ApiException(500, e.getMessage());
    }
  }

  public String invokeAPI(String host, String path, String method, Map<String, String> queryParams, Object body, Map<String, String> headerParams, Map<String, String> formParams, String contentType, Context context) throws InterruptedException, ApiException, ExecutionException {
    return Ion.with(context).load(method, host+path).asString().get();


  }

}
