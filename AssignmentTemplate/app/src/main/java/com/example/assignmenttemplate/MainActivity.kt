package com.example.assignmenttemplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmenttemplate.api.RetrofitHelper
import com.example.assignmenttemplate.api.TrendingMoviesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {

    private lateinit var movieRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieRecyclerView = findViewById<RecyclerView>(R.id.movieRecyclerView)
        movieRecyclerView.layoutManager = LinearLayoutManager(this)

        /*
        runBlocking {
            getTrendingMoviesFromAPI()
        }
        */
        getTrendingMoviesFromAPI()
    }

    private fun getTrendingMoviesFromAPI() {
        val TrendingMovies = RetrofitHelper.getInstance().create(TrendingMoviesApi::class.java)
        // launching a new coroutine

        GlobalScope.launch {
            val results = TrendingMovies.getTrendingMovies().body()
            //Log.d("Results: ", results.toString())

            if (results != null) {
                // This will pass the ArrayList to our Adapter
                val adapter = RecyclerAdapter(results.results, this@MainActivity)

                runOnUiThread {
                    // Have to set the adapter on the UI thread
                    // But I'm ONLY setting the adapter in here, to not do too much work on the UI thread

                    // Setting the Adapter with the recyclerview
                    movieRecyclerView.adapter = adapter
                }
            }
        }

        // My try on making the app load the poster images after done setting everything up
        // The download takes too long, and the app lags
        // The way the poster images are being assigned also messes up with the RecycleView,
        // since the items gets unloaded, so the images assigned are sometimes the wrong ones
        /*
        var adapter: RecyclerAdapter
        GlobalScope.launch {
            val results = TrendingMovies.getTrendingMovies().body()
            if (results != null) {
                // This will pass the ArrayList to our Adapter
                adapter = RecyclerAdapter(results.results, this@MainActivity)

                runOnUiThread {
                    // Have to set the adapter on the UI thread
                    // But I'm ONLY setting the adapter in here, to not do too much work on the UI thread

                    // Setting the Adapter with the recyclerview
                    movieRecyclerView.adapter = adapter
                }

                // DOWNLOAD POSTERS
                /*
                val job = GlobalScope.launch {
                    for(movie in results.results) {
                        val url = URL("https://image.tmdb.org/t/p/original" + movie.poster_path)
                        val input: InputStream = url.openStream()
                        try {
                            val storagePath: File = getCacheDir()
                            val output: OutputStream = FileOutputStream(storagePath.toString() + "/" + movie.id + ".png")
                            try {
                                val buffer = ByteArray(4)
                                var bytesRead = 0
                                while (input.read(buffer, 0, buffer.size).also { bytesRead = it } >= 0) {
                                    output.write(buffer, 0, bytesRead)
                                }
                            } finally {
                                output.close()
                            }
                        } finally {
                            input.close()
                        }
                    }
                }
                job.join()
                */

                //updatePosters()
            }
        }
        */
    }
    /*
    fun updatePosters() {
        GlobalScope.launch {
            println("Updating posters")
            val count = movieRecyclerView.childCount
            for (i in 0..count-1) {
                GlobalScope.launch {
                    val child = movieRecyclerView.getChildAt(i)
                    if(child != null) {
                        val v: ConstraintLayout = movieRecyclerView.getChildAt(i) as ConstraintLayout
                        val vv: View = v.getChildAt(0)
                        if(vv.tag != null) {
                            if (vv is ImageView) {
                                //val cw = ContextWrapper(applicationContext)
                                //val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
                                //directory.absolutePath
                                //val mypath = File(directory, "profile.jpg")

                                val imagePath: String = getCacheDir().toString() + "/" + vv.tag + ".png"
                                val imageBitmap: Bitmap = BitmapFactory.decodeFile(imagePath)
                                //val imageBitmap: Bitmap = BitmapFactory.decodeFile(directory.toString() + "/" + vv.tag + ".png")

                                runOnUiThread {
                                    vv.setImageBitmap(imageBitmap)
                                }
                            }
                        }
                    }
                }
            }
            println("Done updating posters")
        }
    }
    */
}