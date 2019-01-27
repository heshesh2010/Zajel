package soft.deal.best.topline.islamzajel.json;

import android.app.Activity;
import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class JSONParser {

    static String json = "";

    private HttpURLConnection httpConnection;

    public JSONParser() {
    }

    private void openHttpUrlConnection(String urlString,Activity activity) throws IOException {
        Log.d("urlstring in parser", urlString + "");
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();

        httpConnection = (HttpURLConnection) connection;
        httpConnection.setConnectTimeout(30000);
        httpConnection.setRequestMethod("GET");
        httpConnection.setRequestProperty("Content-type", "application/json");
        httpConnection.setRequestProperty("Accept", "application/json");
        httpConnection.connect();
    }

    public JSONObject getJSONHttpURLConnection(String urlString, Activity activity)
            throws IOException, JSONException {

        BufferedReader reader = null;
        StringBuffer output = new StringBuffer("");
        InputStream stream = null;
        JSONObject jsonObject = null;
        try {

            openHttpUrlConnection(urlString,activity);

            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                stream = httpConnection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream,
                        "UTF-8"), 8);
                String line = "";
                while ((line = reader.readLine()) != null)
                    output.append(line + "\n");
                json = output.toString();
                jsonObject = new JSONObject(json);
            }

        } finally {

        }
        return jsonObject;
    }
}
