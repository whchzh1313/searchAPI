package com.example.searchapi.data

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson

object SharedPreferences {
    fun savePref(context: Context, value: DocumentModel, key: String) {
        val sharedPreferences = context.getSharedPreferences("SharedPreference", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(value)
        editor.putString("SearchKey_$key", json)
        editor.apply()
        Log.d("saved", "Data saved")
    }

    fun getAll(context: Context) : String? {
        val sharedPreferences = context.getSharedPreferences("SharedPreference", Activity.MODE_PRIVATE)
        val sharedItems = sharedPreferences.all
        return Gson().toJson(sharedItems)
    }

    fun deletePref(context: Context, uId: String) {
        val sharedPreferences = context.getSharedPreferences("SharedPreference", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("SearchKey_$uId")
        editor.apply()
        Log.d("delete", "Data saved")
    }
}