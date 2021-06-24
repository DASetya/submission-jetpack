package com.dasetya.mymoviesandtvs.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dasetya.mymoviesandtvs.databinding.MovieFragmentBinding
import com.dasetya.mymoviesandtvs.activity.MovieDetailActivity
import com.dasetya.mymoviesandtvs.adapter.MovieAdapter
import com.dasetya.mymoviesandtvs.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment(), MovieAdapter.MovieItemListener {

    private val viewModel: MovieViewModel by viewModels()
    private lateinit var binding: MovieFragmentBinding
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        getMovies()
    }

    private fun setupRecyclerView() {
        adapter = MovieAdapter(this)
        with(binding) {
            rvMovie.layoutManager = LinearLayoutManager(requireContext())
            rvMovie.adapter = adapter
        }
    }

    private fun getMovies() {
        viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
            binding.progressBar.visibility = GONE
            adapter.setItems(ArrayList(movies))
        })
    }


    override fun onClicked(movieId: Int?) {
        val intent = Intent(requireContext(), MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.ID, movieId)
        startActivity(intent)
    }

}