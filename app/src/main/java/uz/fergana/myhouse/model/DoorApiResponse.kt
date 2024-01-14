package uz.fergana.myhouse.model

data class DoorApiResponse(
    val success: Boolean,
    val data: List<Door>
)
data class Door(
    val name: String,
    val room: String?,
    val id: Int,
    val favorites: Boolean,
    val snapshot: String? = null
)