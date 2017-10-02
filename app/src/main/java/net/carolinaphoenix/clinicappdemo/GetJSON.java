package net.carolinaphoenix.clinicappdemo;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class for retrieving JSON objects from API
 *
 * @author Lee Reams
 */


class GetJSON extends AsyncTask<String, Void, String> {

    private static final String baseURL = "http://10.42.8.1:8080/clinic/api";

    protected String doInBackground(String... urls) {
        return getJSON(urls[0], 1000);
    }

    private String getJSON(String url, int timeout) {
        url = baseURL + ((url.startsWith("/"))?"":"/") + url;
        Log.d("JSON", "Processing " + url);
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(timeout);
            c.setReadTimeout(timeout);
            c.connect();
            switch (c.getResponseCode()) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    br.close();
                    return sb.toString();
            }
        } catch (MalformedURLException ex) {
            Log.e("ERROR", "Malformed URL " + ex.getMessage());
        } catch (IOException ex) {
            Log.e("ERROR", "IOException " + ex.getMessage());
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    Log.e("ERROR", "Some kind of URL exception " + ex.getMessage());
                }
            }
        }
        return null;
    }
}
