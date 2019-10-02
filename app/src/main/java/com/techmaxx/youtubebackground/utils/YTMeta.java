/**
 * The api is created by KP for YTPlayer app.
 * https://github.com/KaustubhPatange/YTPlayer/
 *
 * If you want to use it in your project use it without changing the name
 * or this header info.
 *
 * Support the work!
 *
 **/

package com.techmaxx.youtubebackground.utils;

import android.util.Log;

import com.techmaxx.youtubebackground.models.MetaModel;

import org.json.JSONException;
import org.json.JSONObject;

public class YTMeta {

    MetaModel model;

    public YTMeta(String videoID) {
        try {
            HttpHandler handler = new HttpHandler();
            String json = handler.makeServiceCall(
                    "https://www.youtube.com/oembed?url=https://www.youtube.com/watch?v="+videoID+"&format=json");
            if (json==null) {
                Log.e("YTMetaResponse","Null response on: "+videoID);
                return;
            }

            try {
                JSONObject object = new JSONObject(json);
                model = new MetaModel(
                        object.getString("title"),
                        object.getString("author_name"),
                        "https://i.ytimg.com/vi/"+videoID+"/mqdefault.jpg"
                );
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MetaModel getVideMeta() {
        return model;
    }

}
