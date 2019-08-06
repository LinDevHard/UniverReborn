package com.lindevhard.android.univerrebornlite.data.pref

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface PreferenceStorage {
    var isLogged: Boolean
}

class SharedPreferenceStorage @Inject constructor(
        private val context: Context
) : PreferenceStorage {
    val prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)


    companion object {
        const val PREFS_NAME = "universityApp"
        const val PREFS_IS_LOGGED = "is_logged"
    }

    override var isLogged: Boolean by BooleanPreference(prefs, PREFS_IS_LOGGED, false)
}

class BooleanPreference(
        private val preferences: SharedPreferences,
        private val name: String,
        private val defaultValue: Boolean
) : ReadWriteProperty<Any, Boolean> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): Boolean {
        return preferences.getBoolean(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        preferences.edit().putBoolean(name, value).apply()
    }

}
