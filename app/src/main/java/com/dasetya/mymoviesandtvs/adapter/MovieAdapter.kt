package com.dasetya.mymoviesandtvs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dasetya.mymoviesandtvs.BuildConfig.BASE_IMAGE_URL
import com.dasetya.mymoviesandtvs.databinding.ItemListBinding
import com.dasetya.mymoviesandtvs.entity.MovieEntity

class MovieAdapter(private val listener: MovieItemListener) :
    RecyclerView.Adapter<MovieAdapter.TvViewHolder>() {

    interface MovieItemListener {
        fun onClicked(movieId: Int?)
    }

    private val items = ArrayList<MovieEntity>()

    fun setItems(items: ArrayList<MovieEntity>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val binding: ItemListBinding =
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) =
        holder.bind(items[position])

    inner class TvViewHolder(
        private val itemBinding: ItemListBinding,
        private val listener: MovieItemListener
    ) : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var movie: MovieEntity

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: MovieEntity) {
            this.movie = item
            itemBinding.itemTitle.text = item.title
            itemBinding.itemDate.text = item.releaseDate
            itemBinding.itemMovieRating.text = item.voteAverage.toString()
            Glide.with(itemBinding.root)
                .load(BASE_IMAGE_URL + item.posterPath)
                .into(itemBinding.itemPoster)
        }

        override fun onClick(v: View?) {
            listener.onClicked(movie.id)
        }
    }

}


