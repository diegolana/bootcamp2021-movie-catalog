package br.com.diegolana.bootcamp2021

import android.content.res.Resources

data class MovieObj (
    val id : String = "",
    var title : String = "",
    val year : String = "",
    val genres : List<String> = listOf(),
    val ratings : List<Int> = listOf(),
    private val poster : String = "",
    val contentRating : String = "",
    val duration : String = "",
    val releaseDate : String = "",
    val averageRating : Int = -1,
    val originalTitle : String = "",
    val storyline : String = "",
    val actors : List<String> = listOf(),
    val imdbRating : String = "",
    private val posterurl : String = ""
) {

    fun getImageId(res: Resources):Int {
        val name = poster.toLowerCase().take(40)
        return res.getIdentifier(name, "drawable", BuildConfig.APPLICATION_ID)
    }
}