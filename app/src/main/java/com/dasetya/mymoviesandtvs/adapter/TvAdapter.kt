package com.dasetya.mymoviesandtvs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dasetya.mymoviesandtvs.BuildConfig
import com.dasetya.mymoviesandtvs.databinding.ItemListBinding
import com.dasetya.mymoviesandtvs.entity.TvEntity

class TvAdapter(private val listener: TvItemListener) :
    RecyclerView.Adapter<TvAdapter.TvViewHolder>() {

    interface TvItemListener {
        fun onClicked(TvId: Int?)
    }

    private val items = ArrayList<TvEntity>()

    fun setItems(items: ArrayList<TvEntity>) {
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
        private val listener: TvItemListener
    ) : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var Tv: TvEntity

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: TvEntity) {
            this.Tv = item
            itemBinding.itemTitle.text = item.name
            itemBinding.itemDate.text = item.firstAirDate
            itemBinding.itemMovieRating.text = item.voteAverage.toString()
            Glide.with(itemBinding.root)
                .load(BuildConfig.BASE_IMAGE_URL + item.posterPath)
                .into(itemBinding.itemPoster)
        }

        override fun onClick(v: View?) {
            listener.onClicked(Tv.id)
        }
    }

}


