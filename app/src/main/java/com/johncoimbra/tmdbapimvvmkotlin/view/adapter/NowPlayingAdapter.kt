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
import com.johncoimbra.tmdbapimvvmkotlin.listener.MovieListener
import com.johncoimbra.tmdbapimvvmkotlin.model.entity.MovieModel
import com.johncoimbra.tmdbapimvvmkotlin.model.service.Credentials
import com.squareup.picasso.Picasso

class NowPlayingAdapter(
    var mNowPlayingList: List<MovieModel>,
    private val context: Context? = null,
    val movieListener: MovieListener
) :
    RecyclerView.Adapter<NowPlayingAdapter.NowPlayingHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.movie_list_layout, parent, false)
        return NowPlayingHolder(view)
    }

    override fun onBindViewHolder(holder: NowPlayingHolder, position: Int) {
        val mMoviesNowPlaying: MovieModel = mNowPlayingList[position]

        holder.mMovieTitle.text = mMoviesNowPlaying.title

        Picasso.get()
            .load(Credentials.BASE_URL_IMAGE + mMoviesNowPlaying.posterPath)
            .fit().centerCrop()
            .into(holder.mMovieImage)

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

    class NowPlayingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mMovieTitle: TextView = itemView.findViewById(R.id.movie_title)
        val mMovieImage: ImageView = itemView.findViewById(R.id.movie_image)
    }
}




