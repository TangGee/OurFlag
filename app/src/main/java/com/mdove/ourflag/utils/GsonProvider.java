package com.mdove.ourflag.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mdove.ourflag.utils.json.JSONArraySerializer;
import com.mdove.ourflag.utils.json.JSONObjectSerializer;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by MDove on 2019/4/21.
 */
public class GsonProvider {

    private static final Gson sDefault = new GsonBuilder()
        .registerTypeAdapter(JSONObject.class, new JSONObjectSerializer())
        .registerTypeAdapter(JSONArray.class, new JSONArraySerializer())
        .create();

    public static Gson getDefaultGson() {
        return sDefault;
    }
}
