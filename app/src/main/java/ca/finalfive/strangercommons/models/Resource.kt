package ca.finalfive.strangercommons.models

// https://github.com/pokai-huang0828/food-village/blob/main/app/src/main/java/com/example/foodvillage2205/model/responses/Response.kt
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
}
