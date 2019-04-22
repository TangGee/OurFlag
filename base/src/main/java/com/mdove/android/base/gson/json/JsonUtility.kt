package com.mdove.android.base.gson.json

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by MDove on 2019/4/21.
 */
fun JSONObject.toGson(): JsonObject {
    val json = JsonObject()
    keys()?.forEach {
        val value = get(it)
        when (value) {
            is Number -> json.addProperty(it, value)
            is String -> json.addProperty(it, value)
            is Char -> json.addProperty(it, value)
            is Boolean -> json.addProperty(it, value)
            is JSONObject -> json.add(it, value.toGson())
            is JSONArray -> json.add(it, value.toGson())
        }
    }
    return json
}

fun JSONArray.toGson(): JsonArray {
    val json = JsonArray()
    for (i in 0 until length()) {
        val value = get(i)
        when (value) {
            is Number -> json.add(value)
            is String -> json.add(value)
            is Char -> json.add(value)
            is Boolean -> json.add(value)
            is JSONObject -> json.add(value.toGson())
            is JSONArray -> json.add(value.toGson())
        }
    }
    return json
}

