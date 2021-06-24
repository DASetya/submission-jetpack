package com.dasetya.mymoviesandtvs.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.dasetya.mymoviesandtvs.BuildConfig.BASE_IMAGE_URL
import com.dasetya.mymoviesandtvs.R
import com.dasetya.mymoviesandtvs.databinding.ActivityTvDetailBinding
import com.dasetya.mymoviesandtvs.entity.FavEntity
import com.dasetya.mymoviesandtvs.viewmodel.TvDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvDetailActivity : AppCompatActivity() {
    companion object{
        const val ID = "ID"
    }

    private val viewModel: TvDetailViewModel by viewModels()
    private lateinit var binding: ActivityTvDetailBinding
    private var statusFavorite: Boolean? = null
    private var favoriteData = FavEntity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val tvShowId = intent?.getIntExtra(MovieDetailActivity.ID, 0)
        tvShowId?.let {
            getTvShow(it)
            checkFavorite(it)
        }

        binding.imgFav.setOnClickListener {
            if (tvShowId != null) {
                if (statusFavorite == true) {
                    setUnfavorite(tvShowId)
                } else {
                    setFavorite(favoriteData)
                }
            }
        }
    }


    private fun getTvShow(id: Int) {
        viewModel.getTv(id).observe(this, { item ->
            if (item != null) {
                favoriteData = FavEntity(
                    "TVSHOW",
                    item.name,
                    item.posterPath,
                    item.firstAirDate,
                    item.voteAverage,
                    item.id
                )

                binding.progressBar.visibility = View.GONE
                binding.labelOverview.visibility = View.VISIBLE
                binding.txtRating.visibility = View.VISIBLE

                Glide.with(binding.root)
                    .load(BASE_IMAGE_URL + item.posterPath)
                    .into(binding.imgPoster)

                Glide.with(binding.root)
                    .load(BASE_IMAGE_URL + item.backdropPath)
                    .into(binding.imgBackdrop)

                binding.txtTitle.text = item.name
                binding.txtOverview.text = item.overview
                binding.txtRating.text = item.voteAverage.toString()
                binding.txtDate.text = item.firstAirDate

            }
        })
    }


    private fun checkFavorite(id: Int) {
        viewModel.getFavById(id).observe(this, {
            statusFavorite = it != null
            Log.d("TAG", "checkFavorite: $it")
            Log.d("TAG", "checkFavorite: $statusFavorite")
            if (statusFavorite == true) {
                binding.imgFav.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_baseline_favorite_24
                    )
                )
            } else {
                binding.imgFav.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_baseline_favorite_border_24
                    )
                )
            }
        })

    }

    private fun setFavorite(favoriteEntity: FavEntity) {
        viewModel.insertFav(favoriteEntity)
        statusFavorite = true
        favoriteEntity.id?.let { checkFavorite(it) }
    }

    private fun setUnfavorite(id: Int) {
        viewModel.deleteFav(id)
        statusFavorite = false
        checkFavorite(id)
    }
}