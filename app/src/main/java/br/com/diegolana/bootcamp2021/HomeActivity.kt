package br.com.diegolana.bootcamp2021

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import br.com.diegolana.bootcamp2021.databinding.ActivityHomeBinding
import com.google.gson.*

class HomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private val movies by lazy {
        LoadMovies.load(resources, R.raw.movies_coming_soon)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.textViewTitle.text = "OK"
        setupNavController()
        setupRecyclerView()
    }

    private fun setupNavController() {
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            val adapter = binding.recyclerViewMovie.adapter as MoviesAdapter
            binding.drawerLayout.closeDrawers()
            when (menuItem.itemId) {
                R.id.navMenuComingSoon -> {
                    adapter.listMovie = LoadMovies.load(resources, R.raw.movies_coming_soon)
                    true
                }
                R.id.navMenuInTheaters -> {
                    adapter.listMovie = LoadMovies.load(resources, R.raw.movies_in_theaters)
                    true
                }
                R.id.navMenuIndian1 -> {
                    adapter.listMovie = LoadMovies.load(resources, R.raw.top_rated_indian_movies_01)
                    true
                }
                R.id.navMenuIndian2 -> {
                    adapter.listMovie = LoadMovies.load(resources, R.raw.top_rated_indian_movies_02)
                    true
                }
                R.id.navMenuTopRated -> {
                    adapter.listMovie = LoadMovies.load(resources, R.raw.top_rated_movies_01)
                    true
                }
                R.id.navMenuTopRated2 -> {
                    adapter.listMovie = LoadMovies.load(resources, R.raw.top_rated_movies_02)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun setupRecyclerView() {
        val clickListener: MovieItemClickListener = object : MovieItemClickListener {
            override fun onClick(movie: MovieObj) {
                openDetailActivity(movie)
            }
        }
        val adapter = MoviesAdapter(clickListener)
        binding.recyclerViewMovie.adapter = adapter
        binding.recyclerViewMovie.layoutManager = GridLayoutManager(this, 3)
        adapter.listMovie = movies
    }

    private fun openDetailActivity(movie:MovieObj) {
        val intent = Intent(this, DetailActivity::class.java)
        val gsonMovie = Gson().toJson(movie)
        intent.putExtra("MOVIE_GSON", gsonMovie);
        startActivity(intent)
    }

}
