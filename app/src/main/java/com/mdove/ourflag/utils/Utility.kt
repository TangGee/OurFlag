package com.mdove.ourflag.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.fragment.app.Fragment
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.mdove.ourflag.utils.network.MDoveCommonPool
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream


/**
 * Created by MDove on 2019/4/21.
 */


fun Int.dpToPx(context: Context) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    context.resources.displayMetrics
)

fun Float.dpToPx(context: Context) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, context.resources.displayMetrics)

/**
 * T can be Array but can not be Collection
 */
inline fun <reified T : Any> fromJson(json: String): T {
    return GsonProvider.getDefaultGson().fromJson(json, T::class.java)
}

fun Any.toJson() = GsonProvider.getDefaultGson().toJson(this)

fun Any.toJsonObj() = JSONObject(this.toJson())

fun CharSequence.toJsonObj() = JSONObject(this.toString())

inline fun <reified T : Any> fromJson(json: JSONObject) =
    GsonProvider.getDefaultGson().fromJson(json.toString(), T::class.java)

fun Collection<JSONObject>.toJsonArray(): JSONArray {
    val ja = JSONArray()
    forEach { ja.put(it) }
    return ja
}

fun Array<JSONObject>.toJsonArray(): JSONArray {
    val ja = JSONArray()
    forEach { ja.put(it) }
    return ja
}

fun LongArray.toJsonArray(): JSONArray {
    val ja = JSONArray()
    forEach { ja.put(it) }
    return ja
}

fun InputStream.copyToAsync(out: OutputStream, bufferSize: Int = DEFAULT_BUFFER_SIZE) =
    GlobalScope.async(MDoveCommonPool) {
        val l = copyTo(out, bufferSize)
        l
    }

fun InputStream.copyToAsync(out: File, bufferSize: Int = DEFAULT_BUFFER_SIZE) =
    GlobalScope.async(MDoveCommonPool) {
        val l = copyTo(FileOutputStream(out), bufferSize)
        l
    }

/**
 * @param i Intent
 * @param requestCode request code
 * @param useFirstPackage use the first one if more than one handler
 * @param forceShowChooser show selector when more than one handler, even if there is a default handler
 * @param chooserTitle the title of the app selector
 */
@JvmOverloads
fun Activity.safeStartAct(
        i: Intent,
        requestCode: Int = -1,
        useFirstPackage: Boolean = false,
        forceShowChooser: Boolean = false,
        chooserTitle: String = ""
): Boolean {
    val pm = packageManager
    val r = pm.queryIntentActivities(i, PackageManager.MATCH_DEFAULT_ONLY)
    return if (r.size > 0) {
        if (useFirstPackage || r.size == 1) {
            i.`package` = r[0].activityInfo.packageName
        } else if (forceShowChooser) {
            val s = Intent.createChooser(i, chooserTitle)
            return tryStartActivityForResult(s, requestCode)
        }
        return tryStartActivityForResult(i, requestCode)
    } else {
        return false
    }
}

private fun Activity.tryStartActivityForResult(intent: Intent, requestCode: Int): Boolean {
    try {
        startActivityForResult(intent, requestCode)
    } catch (e: Exception) {
        return false
    }
    return true
}

private fun Fragment.tryStartActivityForResult(intent: Intent, requestCode: Int): Boolean {
    try {
        startActivityForResult(intent, requestCode)
    } catch (e: Exception) {
        return false
    }
    return true
}

/**
 * @param i Intent
 * @param requestCode request code
 * @param useFirstPackage use the first one if more than one handler
 * @param forceShowChooser show selector when more than one handler, even if there is a default handler
 * @param chooserTitle the title of the app selector
 */
@JvmOverloads
fun Fragment.safeStartAct(
    i: Intent,
    requestCode: Int = 0,
    useFirstPackage: Boolean = false,
    forceShowChooser: Boolean = false,
    chooserTitle: String = ""
): Boolean {
    val pm = context?.packageManager ?: return false
    val r = pm.queryIntentActivities(i, PackageManager.MATCH_DEFAULT_ONLY)
    return if (r.size > 0) {
        if (useFirstPackage || r.size == 1) {
            i.`package` = r[0].activityInfo.packageName
        } else if (forceShowChooser) {
            val s = Intent.createChooser(i, chooserTitle)
            tryStartActivityForResult(s, requestCode)
            return true
        }
        tryStartActivityForResult(i, requestCode)
        true
    } else {
        false
    }
}

fun JSONObject.forEach(action: (name: String, value: Any) -> Unit) {
    val names = names()

    names?.let {
        for (i in 0 until names.length()) {
            val name = names[i] as String
            action(name, get(name))
        }
    }
}

@SuppressLint("ShowToast")
inline fun Toast.showInCenter() {
    setGravity(Gravity.CENTER, 0, 0)
    show()
}