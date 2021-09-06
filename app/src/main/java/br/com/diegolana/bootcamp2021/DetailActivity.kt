package br.com.diegolana.bootcamp2021

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.diegolana.bootcamp2021.databinding.ActivityDetailBinding
import com.google.gson.Gson

class DetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.lifecycleOwner = this

        getMovie()?.let {
            binding.movie = it
            binding.imageViewMovie.setImageResource(it.getImageId(resources))
        } ?: finish()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getMovie():MovieObj? {
        val extras = intent.extras
        return extras?.let {
            Gson().fromJson(it.getString("MOVIE_GSON"),MovieObj::class.java)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.detail_menu_share) {
            share()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun share() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, binding.movie?.title)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }


}
