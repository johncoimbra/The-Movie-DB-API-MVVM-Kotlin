package com.johncoimbra.tmdbapimvvmkotlin.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.johncoimbra.tmdbapimvvmkotlin.view.adapter.NowPlayingAdapter
import com.johncoimbra.tmdbapimvvmkotlin.databinding.FragmentNowPlayingBinding
import com.johncoimbra.tmdbapimvvmkotlin.listener.MovieListener
import com.johncoimbra.tmdbapimvvmkotlin.model.entity.MovieModel
import com.johncoimbra.tmdbapimvvmkotlin.viewmodel.NowPlayingViewModel

class NowPlayingFragment : Fragment(), MovieListener {
    private val nowPlayingViewModel = NowPlayingViewModel()
    private lateinit var mNowPlayingAdapter: NowPlayingAdapter
    private var _binding: FragmentNowPlayingBinding? = null
    private val binding get() = _binding!!
    var page: Int = 1

    companion object {
        fun newInstance(): NowPlayingFragment {
            val fragmentHome = NowPlayingFragment()
            val arguments = Bundle()
            fragmentHome.arguments = arguments
            return fragmentHome
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNowPlayingBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpObservers()
        nowPlayingViewModel.getNowPlaying(page++)
    }

    private fun setUpObservers() {
        nowPlayingViewModel.nowPlaying.observe(viewLifecycleOwner) {
            //Toast.makeText(activity, "Dados da Api retornados com sucesso", Toast.LENGTH_SHORT).show()
            mNowPlayingAdapter.replaceData(it)
        }
        nowPlayingViewModel.nowPlayingError.observe(viewLifecycleOwner) {
            Toast.makeText(activity, "Ocorreu um erro na chamado do servidor", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun setUpRecyclerView() {
        val recyclerView = binding.recyclerNowPlaying
        mNowPlayingAdapter = NowPlayingAdapter(ArrayList(0), context, this)
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        recyclerView.adapter = mNowPlayingAdapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    nowPlayingViewModel.getNowPlaying(page++)
                }
            }
        })
    }

    override fun onMovieItemClicked(movieModel: MovieModel) {
        val mMovieDetails = Intent(activity, MovieDetailsActivity::class.java)
        mMovieDetails.putExtra("movieId", movieModel.movie_id)
        mMovieDetails.putExtra("posterPath", movieModel.posterPath)
        mMovieDetails.putExtra("title", movieModel.title)
        mMovieDetails.putExtra("voteCount", movieModel.vote_count)
        mMovieDetails.putExtra("popularity", movieModel.popularity)
        activity?.startActivity(mMovieDetails)
    }
}