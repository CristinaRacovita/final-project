package com.example.moviepicker.data.remote

import com.example.moviepicker.data.dtos.*
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface MoviePickerAPI {
    @GET("/credentials")
    fun getCredentials(): Call<List<UserDetailsDTO>>

    @POST("/credentials")
    fun createNewUser(@Body userDTO: UserDTO): Call<UserDTO>

    @POST("/checkUser")
    fun checkUser(@Body userDTO: UserDTO): Call<UserDTO>

    @GET("/movies")
    fun getMoviesForDisplay(): Call<List<DisplayMovieDTO>>

    @GET("/movies/details/{ids}")
    fun getDetailsForMovies(@Path("ids") ids: String): Call<List<DetailsMovieDTO>>

    @POST("/rateMovies")
    fun rateMovies(@Body ratings: List<RatingDTO>): Call<List<RatingDTO>>

    @GET("/prediction/{id}")
    fun getRecommendedMovie(
        @Path("id") id: Int,
        @Query("genre") genre: String,
        @Query("year") year: String
    ): Call<List<RecommendedMovieDTO>>

    @GET("/watchedMovies/{id}")
    fun getWatchedMovies(@Path("id") id: Int): Call<List<WatchedMovieDTO>>

    @Multipart
    @POST("/upload/{id}")
    fun uploadPhoto(@Path("id") id: Int, @Part file: MultipartBody.Part): Call<String>

    @GET("/image/{id}")
    fun getProfileImage(@Path("id") id: Int): Call<ImageDTO>

    @GET("/users/{ids}")
    fun getUsersDetails(@Path("ids") ids: String): Call<List<UserDetailsDTO>>

    @POST("/createGroup")
    fun createGroup(@Body group: GroupDTO): Call<GroupDTO>

    @POST("/addMembers")
    fun addMembers(@Body groupUsers: List<GroupUserDTO>): Call<List<GroupUserDTO>>

    @GET("/group/{id}")
    fun getGroups(@Path("id") id: Int): Call<List<AllGroupsDTO>>

    companion object {
        const val BASE_URL: String = "http://192.168.1.10:8000"
        //            get() = "https://film-server-api.herokuapp.com/"

        fun createAPI(): MoviePickerAPI {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
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