package com.dasetya.mymoviesandtvs.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dasetya.mymoviesandtvs.activity.MovieDetailActivity
import com.dasetya.mymoviesandtvs.adapter.FavMovieAdapter
import com.dasetya.mymoviesandtvs.databinding.FragmentFavMovieBinding
import com.dasetya.mymoviesandtvs.viewmodel.FavMovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavMovieFragment : Fragment(),FavMovieAdapter.MovieItemListener {
    lateinit var binding: FragmentFavMovieBinding
    private lateinit var adapter: FavMovieAdapter
    private val viewModel: FavMovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        getFavoriteMovies()
    }

    private fun setupRecyclerView() {
        adapter = FavMovieAdapter(this)
        with(binding) {
            rvMovie.layoutManager = LinearLayoutManager(requireContext())
            rvMovie.adapter = adapter
        }
    }

    private fun getFavoriteMovies() {
        viewModel.getFavMovie().observe(viewLifecycleOwner, { movies ->
            binding.progressBar.visibility = View.GONE
            val item = movies.filter { it.category == "MOVIE" }
            if (item.isNotEmpty()){
                binding.imgEmpty.visibility = View.INVISIBLE
                binding.txtNoFav.visibility = View.INVISIBLE
                adapter.setItems(ArrayList(item))
            } else {
                binding.imgEmpty.visibility = View.VISIBLE
                binding.txtNoFav.visibility = View.VISIBLE
            }

        })
    }



    override fun onClicked(movieId: Int?) {
        val intent = Intent(requireContext(), MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.ID, movieId)
        startActivity(intent)
    }
}