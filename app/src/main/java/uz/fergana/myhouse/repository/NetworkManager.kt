package uz.fergana.myhouse.repository


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.fergana.myhouse.utils.Constants

object NetworkManager {

    var retrofit: Retrofit? = null
    var api: ApiService? = null

    fun getApiService(): ApiService {
        if (api == null) {
            retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()
            api = retrofit!!.create(ApiService::class.java)
        }
        return api!!
    }
}