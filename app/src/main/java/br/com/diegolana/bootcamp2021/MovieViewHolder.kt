package br.com.diegolana.bootcamp2021

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.diegolana.bootcamp2021.databinding.MainItemBinding

class MovieViewHolder private constructor(private val binding: MainItemBinding, private val clickListener: MovieItemClickListener) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: MovieObj) {
        val res = itemView.context.resources
        binding.textViewMainHeader.text = movie.title
        binding.imageViewMovie.setImageResource(movie.getImageId(res))
        binding.imageViewMovie.setOnClickListener { clickListener.onClick(movie) }

    }

    companion object {
        fun from(parent: ViewGroup, clickListener: MovieItemClickListener): MovieViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = MainItemBinding.inflate(layoutInflater, parent, false)
            return MovieViewHolder(binding, clickListener)
        }
    }
}