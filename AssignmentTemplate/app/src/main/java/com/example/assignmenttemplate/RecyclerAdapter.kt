package com.example.assignmenttemplate

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmenttemplate.api.ResponseMovie


internal class RecyclerAdapter(private var moviesList: Array<ResponseMovie>, var context: Context) : RecyclerView.Adapter<RecyclerAdapter.MovieViewHolder>() {

    internal inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var movieTitleTextView: TextView = view.findViewById(R.id.movieTitle)
        var movieReleaseDateTextView: TextView = view.findViewById(R.id.movieReleaseDate)
        var moviePoster: ImageView = view.findViewById(R.id.posterImageView)
        var moviePopularityTextView: TextView = view.findViewById(R.id.moviePopularity)
        var movieContainer: ConstraintLayout = view.findViewById(R.id.container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieView = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)

        return MovieViewHolder(movieView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val posterPath = moviesList[position].poster_path
        val releaseDate = moviesList[position].release_date
        val genreIDs = moviesList[position].genre_ids
        val ID = moviesList[position].id
        val movieTitle = moviesList[position].title
        val backdropPath = moviesList[position].backdrop_path
        val popularity = moviesList[position].popularity
        val voteAverage = moviesList[position].vote_average
        val voteCount = moviesList[position].vote_count
        val overview = moviesList[position].overview

        holder.movieReleaseDateTextView.text = releaseDate
        holder.movieTitleTextView.text = movieTitle
        holder.moviePopularityTextView.text = "Score: $popularity"
        holder.moviePoster.setTag(ID)

        holder.movieContainer.setOnClickListener {
            //println("You clicked the movie: $movieTitle, id: $ID" )
            val intent = Intent(context, MovieActivity::class.java)
            // Pass the movies data to next activity
            intent.putExtra("posterPath", posterPath)
            intent.putExtra("releaseDate", releaseDate)
            intent.putExtra("genreIDs", genreIDs.toString())
            intent.putExtra("ID", ID.toString())
            intent.putExtra("movieTitle", movieTitle)
            intent.putExtra("backdropPath", backdropPath)
            intent.putExtra("popularity", popularity.toString())
            intent.putExtra("voteAverage", voteAverage.toString())
            intent.putExtra("voteCount", voteCount.toString())
            intent.putExtra("overview", overview)
            // start next activity
            startActivity(context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }
}