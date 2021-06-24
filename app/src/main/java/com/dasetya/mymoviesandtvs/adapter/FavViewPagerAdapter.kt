package com.dasetya.mymoviesandtvs.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dasetya.mymoviesandtvs.fragment.FavMovieFragment
import com.dasetya.mymoviesandtvs.fragment.FavTvFragment

class FavViewPagerAdapter(
    fa: FragmentActivity
) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FavMovieFragment()
            1 -> FavTvFragment()
            else -> FavMovieFragment()
        }
    }

}