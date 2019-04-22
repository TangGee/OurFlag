package com.mdove.android.base.gson

import com.google.gson.GsonBuilder
import com.mdove.android.base.gson.json.JSONArraySerializer
import com.mdove.android.base.gson.json.JSONObjectSerializer
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by MDove on 2019/4/21.
 */
object GsonProvider {
    val defaultGson = GsonBuilder()
            .registerTypeAdapter(JSONObject::class.java, JSONObjectSerializer())
            .registerTypeAdapter(JSONArray::class.java, JSONArraySerializer())
            .create()
}
