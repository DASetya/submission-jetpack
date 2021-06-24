package com.dasetya.mymoviesandtvs.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dasetya.mymoviesandtvs.activity.TvDetailActivity
import com.dasetya.mymoviesandtvs.adapter.FavTvAdapter
import com.dasetya.mymoviesandtvs.databinding.FragmentFavTvBinding
import com.dasetya.mymoviesandtvs.viewmodel.FavTvViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavTvFragment : Fragment(), FavTvAdapter.TvShowItemListener {
    lateinit var binding: FragmentFavTvBinding
    private lateinit var adapter: FavTvAdapter
    private val viewModel: FavTvViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        getFavoriteTvShow()
    }

    private fun setupRecyclerView() {
        adapter = FavTvAdapter(this)
        with(binding) {
            rvTv.layoutManager = LinearLayoutManager(requireContext())
            rvTv.adapter = adapter
        }
    }

    private fun getFavoriteTvShow() {
        viewModel.getFavTv().observe(viewLifecycleOwner, { tvShow ->
            binding.progressBar.visibility = View.GONE
            val item = tvShow.filter { it.category == "TVSHOW" }
            if (item.isNotEmpty()) {
                binding.imgEmpty.visibility = View.INVISIBLE
                binding.txtNoFav.visibility = View.INVISIBLE
                adapter.setItems(ArrayList(item))
            } else {
                binding.imgEmpty.visibility = View.VISIBLE
                binding.txtNoFav.visibility = View.VISIBLE
            }

        })
    }


    override fun onClicked(tvShowId: Int?) {
        val intent = Intent(requireContext(), TvDetailActivity::class.java)
        intent.putExtra(TvDetailActivity.ID, tvShowId)
        startActivity(intent)
    }
}