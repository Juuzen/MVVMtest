package com.juuzen.forecastmvvm.data.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.juuzen.forecastmvvm.utils.UnitSystem

const val UNIT_SYSTEM = "UNIT_SYSTEM"

class UnitProviderImpl(context: Context) : UnitProvider {
    private val appContext = context.applicationContext
    private val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    override fun getUnitSystem(): UnitSystem {
        val selected = preferences.getString(UNIT_SYSTEM, UnitSystem.METRIC.system)
        return UnitSystem.valueOf(selected!!)
    }

    override fun getUnitCode(): String {
        val selected = getUnitSystem()
        return selected.code
    }
}