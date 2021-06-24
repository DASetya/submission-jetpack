package com.dasetya.mymoviesandtvs.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dasetya.mymoviesandtvs.databinding.TvFragmentBinding
import com.dasetya.mymoviesandtvs.activity.TvDetailActivity
import com.dasetya.mymoviesandtvs.adapter.TvAdapter
import com.dasetya.mymoviesandtvs.viewmodel.TvViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvFragment : Fragment(), TvAdapter.TvItemListener {

    private val viewModel: TvViewModel by viewModels()
    private lateinit var binding: TvFragmentBinding
    private lateinit var adapter: TvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TvFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        getTvs()
    }

    private fun setupRecyclerView() {
        adapter = TvAdapter(this)
        with(binding) {
            rvTv.layoutManager = LinearLayoutManager(requireContext())
            rvTv.adapter = adapter
        }
    }

    private fun getTvs() {
        viewModel.getTvs().observe(viewLifecycleOwner, {
            binding.progressBar.visibility = View.GONE
            adapter.setItems(ArrayList(it))
        })
    }

    override fun onClicked(TvId: Int?) {
        val intent = Intent(requireContext(), TvDetailActivity::class.java)
        intent.putExtra(TvDetailActivity.ID, TvId)
        startActivity(intent)
    }

}