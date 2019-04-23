package com.mdove.ourflag.config

import android.content.Context
import android.content.SharedPreferences
import com.mdove.android.base.AbsApplication

/**
 * Created by MDove on 2019/4/23.
 */
object AppSpConfig {
    private val PREFS_FILE = "our_flag"
    private var sPrefs: SharedPreferences=AbsApplication.inst.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
    private val KEY_HAS_LOGIN:String="key_has_login"
    fun isHasLogin(): Boolean {
        return sPrefs.getBoolean(KEY_HAS_LOGIN, false)
    }

    fun setHasLogin() {
        setHasLogin(true)
    }

    fun setHasLogin(login: Boolean) {
        val editor = sPrefs.edit()
        editor.putBoolean(KEY_HAS_LOGIN, login)
        editor.apply()
    }

}