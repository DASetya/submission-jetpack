package com.dasetya.mymoviesandtvs.response

import com.dasetya.mymoviesandtvs.entity.TvEntity
import com.google.gson.annotations.SerializedName

data class TvResponse(

    @field:SerializedName("page")
	val page: Int? = null,

    @field:SerializedName("total_pages")
	val totalPages: Int? = null,

    @field:SerializedName("results")
	val results: List<TvEntity>? = null,

    @field:SerializedName("total_results")
	val totalResults: Int? = null
)

