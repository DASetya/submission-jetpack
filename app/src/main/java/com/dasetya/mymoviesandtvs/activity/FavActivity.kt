package com.dasetya.mymoviesandtvs.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dasetya.mymoviesandtvs.R
import com.dasetya.mymoviesandtvs.adapter.FavViewPagerAdapter
import com.dasetya.mymoviesandtvs.databinding.ActivityFavBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavActivity : AppCompatActivity() {
    private val tabTitles = arrayOf(
        R.string.movies,
        R.string.tv
    )

    lateinit var binding: ActivityFavBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val sectionsPagerAdapter = FavViewPagerAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(tabTitles[position])
            binding.viewPager.setCurrentItem(tab.position, true)
        }.attach()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}