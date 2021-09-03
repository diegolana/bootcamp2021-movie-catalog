package br.com.diegolana.bootcamp2021

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MoviesAdapter(private val clickListener: MovieItemClickListener) : RecyclerView.Adapter<MovieViewHolder>() {

    var listMovie = listOf<MovieObj>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent, clickListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }
}