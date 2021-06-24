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
import com.dasetya.mymoviesandtvs.databinding.AcitivityMovieDetailBinding
import com.dasetya.mymoviesandtvs.entity.FavEntity
import com.dasetya.mymoviesandtvs.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {
    companion object {
        const val ID = "ID"
    }

    private val viewModel: MovieDetailViewModel by viewModels()
    private lateinit var binding: AcitivityMovieDetailBinding
    private var statusFavorite: Boolean? = null
    private var favoriteData = FavEntity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AcitivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val movieID = intent?.getIntExtra(ID, 0)
        movieID?.let {
            getMovie(it)
            checkFavorite(it)
        }

        binding.imgFav.setOnClickListener {
            if (movieID != null) {
                if (statusFavorite == true) {
                    setUnfavorite(movieID)
                } else {
                    setFavorite(favoriteData)
                }
            }
        }
    }

    private fun getMovie(id: Int) {
        viewModel.getMovie(id).observe(this, { item ->
            if (item != null) {
                favoriteData = FavEntity(
                    "MOVIE",
                    item.title,
                    item.posterPath,
                    item.releaseDate,
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

                binding.txtTitle.text = item.title
                binding.txtOverview.text = item.overview
                binding.txtRating.text = item.voteAverage.toString()
                binding.txtDate.text = item.releaseDate

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