package soft.deal.best.topline.islamzajel.json;

import android.app.Activity;
import android.os.Build;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class GetJSONObject {

    public static JSONObject getJSONObject(String url ,Activity activity) throws IOException,
            JSONException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        // Use HttpURLConnection
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.FROYO) {
            jsonObject = jsonParser.getJSONHttpURLConnection(url,activity);
        }
        return jsonObject;
    }
}
