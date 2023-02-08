package com.ayan.rnetwork;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

public class RNetwork implements NetworkInterface {

   private static OkHttpClient okHttpClient = null;
   private static RNetwork rNetwork = null;

   private OkHttpClient buildClient() {
      if (okHttpClient != null) return okHttpClient;

      OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
              .connectTimeout(10000, TimeUnit.MILLISECONDS)
              .readTimeout(10000, TimeUnit.MILLISECONDS);

      HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
      httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);

      return  okHttpClientBuilder.build();
   }

   private Request.Builder buildRequest(String url) {
      return new Request.Builder()
              .url(url);
   }

   private ResponseBody getResponse(Request request) {
      OkHttpClient client = buildClient();
      try {
         Response response = client.newCall(request).execute();
         return response.body();
      } catch (IOException e) {
         e.printStackTrace();
         return null;
      }
   }

   @Override
   public  JSONObject fetchCustomUI(String url) {
      Request request = buildRequest(url).build();
      ResponseBody response = getResponse(request);

      if(response!=null){
         try {
            return new JSONObject(response.string());
         } catch (JSONException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         }

      }

      return null;
   }

   @Override
   public byte[] fetchLogo(String url) {
      Request request = buildRequest(url).build();
      ResponseBody response = getResponse(request);
      if(response!=null) {
         try {
            return response.source().readByteArray();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }

      return new byte[0];
   }

   public static RNetwork getInstance(){

      if(rNetwork!=null)
         return rNetwork;

      rNetwork = new RNetwork();
      return rNetwork;

   }
}
