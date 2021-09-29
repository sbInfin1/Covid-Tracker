package android.example.covid_tracker_remastered.network

import com.google.gson.GsonBuilder
import com.google.gson.internal.UnsafeAllocator.create
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://corona.lmao.ninja/v2/"

//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()

private val gson = GsonBuilder().create()

/** Use the Retrofit builder to build a retrofit object using a Moshi converter with
 * our Moshi object
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getProperties] method
 */
interface CovidApiService {
    /**
     * The @GET annotation indicates that the "countries?yesterday&sort" endpoint
     * will be requested with the GET HTTP method
     */
    @GET("countries?yesterday&sort")
    fun getProperties(): Call<List<CovidProperty>>
}

/**
 * A public Api object that exposes the lazy-initiated Retrofit service
 */
object CovidApi {
    val retrofitService: CovidApiService by lazy { retrofit.create(CovidApiService::class.java) }
}