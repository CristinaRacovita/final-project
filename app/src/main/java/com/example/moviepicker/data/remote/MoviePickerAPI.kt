package com.example.moviepicker.data.remote

import com.example.moviepicker.data.UserDTO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface MoviePickerAPI {
    @GET("credentials.json")
    fun getCredentials(): Call<List<UserDTO>>

    @PUT("credentials/{nr}.json")
    fun createNewUser(@Path("nr") number: Int, @Body userDTO: UserDTO): Call<UserDTO>

    companion object {
        private val BASE_URL: String
            get() = "https://movie-picker-21f87-default-rtdb.firebaseio.com/"

        fun createAPI(): MoviePickerAPI {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
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