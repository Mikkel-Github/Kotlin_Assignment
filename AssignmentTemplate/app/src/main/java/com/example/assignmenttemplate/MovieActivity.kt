package com.example.assignmenttemplate

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_activity)

        // Get all the data from the last Activity
        findViewById<TextView>(R.id.TitleTextView).text = intent.getStringExtra("movieTitle")
        findViewById<TextView>(R.id.releaseDateTextView).text = "Released: " + intent.getStringExtra("releaseDate")
        findViewById<TextView>(R.id.popularityTextView).text = "Popularity: " + intent.getStringExtra("popularity")
        findViewById<TextView>(R.id.test).text = "Score: " + intent.getStringExtra("voteAverage")
        findViewById<TextView>(R.id.voteCountTextView).text = "Total votes: " + intent.getStringExtra("voteCount")
        findViewById<TextView>(R.id.overviewTextView).text = intent.getStringExtra("overview")
        val backdropImageView = findViewById<ImageView>(R.id.imageView)

        // Start an coroutine to download the backdrop and set it
        GlobalScope.launch {
            try {
                val url = URL("https://image.tmdb.org/t/p/original" + intent.getStringExtra("backdropPath"))
                val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                if (backdropImageView != null) {
                    runOnUiThread {
                        backdropImageView.setImageBitmap(bmp)
                    }
                }
            } catch (e: Exception) {
                Log.d("Error: ", e.toString())
            }
        }

        // todo: make call to TheMovieDB to get the genre name from the genre ID
        /*
        val linearLayout: LinearLayout = findViewById<LinearLayout>(R.id.genreLinearLayout)
        val genre = TextView(this)
        val genres = intent.getStringExtra("genreIDs")!!.split(",")
        genre.text = genres!![0]
        linearLayout.addView(genre)
        */
    }
}