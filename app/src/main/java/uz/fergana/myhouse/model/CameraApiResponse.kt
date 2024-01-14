package uz.fergana.myhouse.model

data class BaseModel(
    val success: Boolean,
    val data: ApiData
)
data class ApiData(
    val room: List<String>,
    val cameras: List<Camera>
)
data class Camera(
    val name: String,
    val snapshot: String,
    val room: String?,
    val id: Int,
    val favorites: Boolean,
    val rec: Boolean
)