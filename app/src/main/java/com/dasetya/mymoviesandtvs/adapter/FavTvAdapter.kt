package com.dasetya.mymoviesandtvs.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dasetya.mymoviesandtvs.BuildConfig.BASE_IMAGE_URL
import com.dasetya.mymoviesandtvs.databinding.ItemListBinding
import com.dasetya.mymoviesandtvs.entity.FavEntity

class FavTvAdapter(private val listener: TvShowItemListener) :
    PagedListAdapter<FavEntity, FavTvAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<FavEntity> =
            object : DiffUtil.ItemCallback<FavEntity>() {
                override fun areItemsTheSame(
                    oldFav: FavEntity,
                    newFav: FavEntity
                ): Boolean {
                    return oldFav.title == newFav.title && oldFav.releaseDate == newFav.releaseDate
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldFav: FavEntity,
                    newFav: FavEntity
                ): Boolean {
                    return oldFav == newFav
                }
            }
    }

    interface TvShowItemListener {
        fun onClicked(tvShowId: Int?)
    }

    private val items = ArrayList<FavEntity>()

    fun setItems(items: ArrayList<FavEntity>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding: ItemListBinding =
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) =
        holder.bind(items[position])

    inner class TvShowViewHolder(
        private val itemBinding: ItemListBinding,
        private val listener: TvShowItemListener
    ) : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private var movie: FavEntity? = null

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: FavEntity) {
            this.movie = item
            itemBinding.itemTitle.text = item.title
            itemBinding.itemDate.text = item.releaseDate
            itemBinding.itemMovieRating.text = item.voteAverage.toString()
            Glide.with(itemBinding.root)
                .load(BASE_IMAGE_URL + item.posterPath)
                .into(itemBinding.itemPoster)
        }

        override fun onClick(v: View?) {
            listener.onClicked(movie?.id)
        }
    }

}