package com.mdove.android.base.init

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.getOrElse
import androidx.core.view.children
import com.mdove.android.base.AbsApplication
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by MDove on 2019/4/22.
 */
val sViewLock = ConcurrentHashMap<Int, Any>()
val sViewCachePool = SparseArray<ArrayList<View?>>()
val sInflater = LayoutInflater.from(AbsApplication.inst)

fun getViewFromPool(layoutId: Int, parent: ViewGroup?, context: Context?): View {
    var view = getViewFromCache(layoutId, context)
    if (view != null) {
        return view
    }

    val lock = sViewLock.getOrPut(layoutId) {
        Any()
    }

    synchronized(lock) {
        view = getViewFromCache(layoutId, context)
        view?.let {
            return it
        }

        parent?.let {
            return LayoutInflater.from(context).inflate(layoutId, parent, false)
        }

        return LayoutInflater.from(context).inflate(layoutId, null)
    }
}

fun doPreLoadCreateView(layoutId: Int) {
    val lock = sViewLock.getOrPut(layoutId) {
        Any()
    }
    synchronized(lock) {
        try {
            val view = sInflater.inflate(layoutId, null)
            val viewList = sViewCachePool.getOrElse(layoutId) {
                ArrayList()
            }
            viewList.forEachIndexed { index, view ->
                if (view == null) {
                    viewList[index] = view
                    return
                }
            }

            viewList.add(view)
            sViewCachePool.put(layoutId, viewList)
        } catch (e: Exception) {
        }
    }
}


private fun getViewFromCache(layoutId: Int, context: Context?): View? {
    var list = sViewCachePool[layoutId]
    list?.forEachIndexed { index, view ->
        if (view != null && view.parent == null) {
            list[index] = null
            if (view.context != context) {
                changeViewContext(view, context)
            }
            return view
        }
    }

    return null
}


private fun changeViewContext(view: View, context: Context?) {
    try {
        if (view is ViewGroup) {
            view.children.iterator().forEach { child ->
                changeViewContext(child, context)
            }
        }
        val viewClass = View::class.java
        val contextFiled = viewClass.getDeclaredField("mContext")
        contextFiled.isAccessible = true
        contextFiled.set(view, context)
        contextFiled.isAccessible = false
    } catch (e: Exception) {
//        ExceptionLogger.safeLogException(e)
    }
}



