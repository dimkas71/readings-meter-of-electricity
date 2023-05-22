package ua.dimkas71.common

import android.content.Context
import android.content.SharedPreferences

const val URI_KEY = "URI_KEY"
const val USER_KEY = "USER_KEY"
const val PASSWORD_KEY = "PASSWORD_KEY"
const val START_DATE_BY_DEFAULT_KEY = "START_DATE_BY_DEFAULT_KEY"
const val SEARCH_VARIANT_KEY = "SEARCH_VARIANT_KEY"

enum class SearchVariant(val text: String) {

    ByContractNumber("By contract number"),
    ByNumber("By number"),
    ByDescription("By description")

}

fun getSharedPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences(
        "${context.applicationInfo.name}.prefs", Context.MODE_PRIVATE
    )
}


