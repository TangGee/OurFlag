package com.mdove.ourflag.utils.json

import com.mdove.ourflag.utils.forEach
import org.json.JSONObject

/**
 * Created by MDove on 2019/4/21.
 */
fun JSONObject.putAll(map: Map<String, Any>?) {
    map?.forEach {
        this.put(it.key, it.value)
    }
}

fun JSONObject.putAll(obj: JSONObject?) = apply {
    obj?.forEach { name, value ->
        put(name, value)
    }
}


fun JSONObject.putAllToMap(map: MutableMap<String, Any>) {
    this.forEach { key, value ->
        map[key] = value
    }
}