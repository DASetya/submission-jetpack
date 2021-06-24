package com.dasetya.mymoviesandtvs.entity

import com.google.gson.annotations.SerializedName

data class TvEntity(

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("first_air_date")
	val firstAirDate: String? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double = 0.0,

	@field:SerializedName("id")
	val id: Int? = null,

)
