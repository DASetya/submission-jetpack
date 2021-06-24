package com.dasetya.mymoviesandtvs.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dasetya.mymoviesandtvs.fragment.MovieFragment
import com.dasetya.mymoviesandtvs.fragment.TvFragment

class ViewPagerAdapter( fa: FragmentActivity ) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieFragment()
            1 -> TvFragment()
            else -> MovieFragment()
        }
    }

}