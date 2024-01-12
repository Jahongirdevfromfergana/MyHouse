package uz.fergana.myhouse.model

data class BaseResponseModel<T>(
    val success: Boolean,
    val data: T
)
