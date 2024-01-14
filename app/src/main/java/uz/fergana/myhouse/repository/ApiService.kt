package uz.fergana.myhouse.repository


import retrofit2.http.GET
import uz.fergana.myhouse.model.BaseModel
import uz.fergana.myhouse.model.DoorApiResponse

interface ApiService {
    @GET("cameras")
    suspend fun getCamera(): BaseModel
    @GET("doors")
    suspend fun getDoorData(): DoorApiResponse

}