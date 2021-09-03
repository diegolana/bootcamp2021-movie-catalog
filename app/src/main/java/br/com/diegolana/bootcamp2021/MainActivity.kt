package br.com.diegolana.bootcamp2021

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val movies by lazy {
        LoadMovies.load(resources, R.raw.top_rated_movies_01)
    }

    private val textViewTitle by lazy {
        findViewById<TextView>(R.id.textViewTitle)
    }

    private val textViewStoryline by lazy {
        findViewById<TextView>(R.id.textViewStoryline)
    }

    private val imageView by lazy {
        findViewById<ImageView>(R.id.imageViewMovie)
    }

    private var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        next()
    }

    fun next(view: View? = null) {
        val movie = movies[num]
        textViewTitle.text = movie.title
        imageView.setImageResource(movie.getImageId(resources))
        textViewStoryline.text = movie.storyline

        num = if (num < movies.size-1) num+1 else 0
    }
}
