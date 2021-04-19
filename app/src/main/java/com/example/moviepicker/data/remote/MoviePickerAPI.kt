package com.example.moviepicker.data.remote

import com.example.moviepicker.data.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface MoviePickerAPI {
    @GET("credentials")
    fun getCredentials(): Call<List<UserDTO>>

    @POST("credentials")
    fun createNewUser(@Body userDTO: UserDTO): Call<UserDTO>

    @GET("movies")
    fun getMoviesForDisplay(): Call<List<DisplayMovieDTO>>

    @GET("movies/details/{ids}")
    fun getDetailsForMovies(@Path("ids") ids: String): Call<List<DetailsMovieDTO>>

    @POST("rateMovies")
    fun rateMovies(@Body ratings: List<RatingDTO>): Call<List<RatingDTO>>

    @GET("prediction/{id}")
    fun getRecommendedMovie(@Path("id") id: Int): Call<List<RecommendedMovieDTO>>

    @GET("watchedMovies/{id}")
    fun getWatchedMovies(@Path("id") id: Int): Call<List<WatchedMovieDTO>>

    companion object {
        private val BASE_URL: String
            //            get() = "https://film-server-api.herokuapp.com/"
            get() = "http://192.168.1.10:8000/"

        fun createAPI(): MoviePickerAPI {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MoviePickerAPI::class.java)
        }
    }
}