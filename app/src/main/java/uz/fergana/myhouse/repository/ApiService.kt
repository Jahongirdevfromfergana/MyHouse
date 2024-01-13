package uz.fergana.myhouse.repository


import retrofit2.Call
import retrofit2.http.*
import uz.fergana.myhouse.model.BaseModel
import uz.fergana.myhouse.model.BaseResponseModel
import uz.fergana.myhouse.model.CameraModel

interface ApiService {
//
//    @GET("doors/")
//    fun getCamera(): Call<BaseResponseModel<>>

    @GET("doors/")
    fun getCamera2(): Call<BaseResponseModel<List<CameraModel>>>


}