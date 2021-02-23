package com.juuzen.forecastmvvm.data.provider

import com.juuzen.forecastmvvm.utils.UnitSystem

interface UnitProvider {
    fun getUnitSystem() : UnitSystem
    fun getUnitCode() : String
}