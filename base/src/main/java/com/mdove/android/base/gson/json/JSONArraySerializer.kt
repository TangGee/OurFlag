package com.mdove.android.base.gson.json

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import org.json.JSONArray
import java.lang.reflect.Type


/**
 * Created by MDove on 2019/4/21.
 */
class JSONArraySerializer : JsonSerializer<JSONArray> {
    override fun serialize(
            src: JSONArray?,
            typeOfSrc: Type?,
            context: JsonSerializationContext?
    ): JsonElement {
        return src?.toGson() ?: JsonArray()
    }
}