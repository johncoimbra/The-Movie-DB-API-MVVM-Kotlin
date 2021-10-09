package com.johncoimbra.tmdbapimvvmkotlin.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.johncoimbra.tmdbapimvvmkotlin.R
import com.johncoimbra.tmdbapimvvmkotlin.databinding.MovieListLayoutBinding
import com.johncoimbra.tmdbapimvvmkotlin.listener.MovieListener
import com.johncoimbra.tmdbapimvvmkotlin.model.entity.MovieModel
import com.johncoimbra.tmdbapimvvmkotlin.model.service.Credentials
import com.squareup.picasso.Picasso

class NowPlayingAdapter(
    private var mNowPlayingList: List<MovieModel>,
    private val context: Context? = null,
    private val movieListener: MovieListener
): RecyclerView.Adapter<NowPlayingAdapter.NowPlayingHolder>() {

    private lateinit var binding: MovieListLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingHolder {
        binding = MovieListLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return NowPlayingHolder(binding)
    }

    override fun onBindViewHolder(holder: NowPlayingHolder, position: Int) {
        val mMoviesNowPlaying: MovieModel = mNowPlayingList[position]
        holder.bind(mMoviesNowPlaying)
        holder.itemView.setOnClickListener {
            movieListener.onMovieItemClicked(mMoviesNowPlaying)
        }
    }

    override fun getItemCount(): Int = mNowPlayingList.size

    @SuppressLint("NotifyDataSetChanged")
    fun replaceData(mMoviesNowPlaying: List<MovieModel>) {
        val mListMovies = ArrayList<MovieModel>()
        mListMovies.addAll(mNowPlayingList)
        mListMovies.addAll(mMoviesNowPlaying)
        setList(mListMovies)
        notifyDataSetChanged()
    }

    private fun setList(mListMovies: List<MovieModel>) {
        mNowPlayingList = mListMovies
    }

    class NowPlayingHolder(binding: MovieListLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        private val mMovieTitle = binding.movieTitle
        private val mMovieImage = binding.movieImage

        fun bind(movie: MovieModel) {
            mMovieTitle.text = movie.title
            Picasso.get()
                .load(Credentials.BASE_URL_IMAGE + movie.posterPath)
                .fit().centerCrop()
                .into(mMovieImage)
        }
    }
}




