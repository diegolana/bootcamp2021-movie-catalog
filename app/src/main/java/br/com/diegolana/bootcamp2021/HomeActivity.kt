package br.com.diegolana.bootcamp2021

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
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
        setupNavController()
        setupRecyclerView()

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (binding.drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                binding.drawerLayout.closeDrawer(Gravity.LEFT)

            } else {
                binding.drawerLayout.openDrawer(Gravity.LEFT)
            }
            return true;
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupNavController() {
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            val adapter = binding.recyclerViewMovie.adapter as MoviesAdapter
            binding.drawerLayout.closeDrawers()
            val rawId = when (menuItem.itemId) {
                R.id.navMenuComingSoon -> R.raw.movies_coming_soon
                R.id.navMenuInTheaters -> R.raw.movies_in_theaters
                R.id.navMenuIndian1 -> R.raw.top_rated_indian_movies_01
                R.id.navMenuIndian2 -> R.raw.top_rated_indian_movies_02
                R.id.navMenuTopRated -> R.raw.top_rated_movies_01
                R.id.navMenuTopRated2 -> R.raw.top_rated_movies_02
                else -> R.raw.movies_coming_soon
            }
            adapter.listMovie = LoadMovies.load(resources, rawId)
            true
        }

        binding.drawerLayout.addDrawerListener(object : DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}

            override fun onDrawerStateChanged(newState: Int) {}

            override fun onDrawerOpened(drawerView: View) {
                supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_open)
            }

            override fun onDrawerClosed(drawerView: View) {
                supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
            }



        })

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

    private fun openDetailActivity(movie: MovieObj) {
        val intent = Intent(this, DetailActivity::class.java)
        val gsonMovie = Gson().toJson(movie)
        intent.putExtra("MOVIE_GSON", gsonMovie);
        startActivity(intent)
    }

}
