package com.plugin.httpsTrust;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import org.apache.cordova.*;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
/**
 * This class echoes a string called from JavaScript.
 */
public class HTTPSTrustPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("ignoreCerts")) {
            this.ignoreCerts(callbackContext);
            return true;
        }
        return false;
    }

    private void ignoreCerts(CallbackContext callbackContext) {
            TrustManager[] trustAllCerts = new TrustManager[]{
              new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                  return null;
                }
                public void checkClientTrusted(
                  java.security.cert.X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(
                  java.security.cert.X509Certificate[] certs, String authType) {
                }
              }
            };

            // Install the all-trusting trust manager
            // Try "SSL" or Replace with "TLS"
            try {
              SSLContext sc = SSLContext.getInstance("SSL");
              sc.init(null, trustAllCerts, new java.security.SecureRandom());
              HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
              HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                  return true;
                }
              });
            } catch (Exception e) {
              callbackContext.error("error");
            }
            callbackContext.success("success");
    }
}
