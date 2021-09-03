package br.com.diegolana.bootcamp2021

import android.content.res.Resources
import androidx.annotation.RawRes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LoadMovies {

    companion object {
        fun load(res: Resources, @RawRes rawResId: Int): List<MovieObj> = readRawJson(res, rawResId)

        private inline fun <reified T> readRawJson(res: Resources, @RawRes rawResId: Int): T {
            res.openRawResource(rawResId).bufferedReader().use {
                return Gson().fromJson<T>(it, object: TypeToken<T>() {}.type)
            }
        }
    }

}