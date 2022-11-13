package ca.finalfive.friendsup.models

// https://github.com/pokai-huang0828/food-village/blob/main/app/src/main/java/com/example/foodvillage2205/model/responses/Response.kt
/**
 * Resource for Firestore responses
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    /**
     * For success
     */
    class Success<T>(data: T): Resource<T>(data)

    /**
     * For Error
     */
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
}