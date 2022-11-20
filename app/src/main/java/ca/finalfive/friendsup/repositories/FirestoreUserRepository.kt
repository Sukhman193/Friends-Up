package ca.finalfive.friendsup.repositories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ca.finalfive.friendsup.helpers.Error
import ca.finalfive.friendsup.models.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject

/**
 * Constants Class - holds the USERS field from collection
 */
class Constants {
    companion object {
        // user's collection name
        const val USERS = "Users"
    }
}

/**
 * FirestoreUserRepository class - configurations and functions for firestore database
 */
class FirestoreUserRepository() {
    // get the instance of the firestore
    private val firestore = FirebaseFirestore.getInstance()

    // get the collection of the Users
    private val collection = firestore.collection(Constants.USERS)

    // firestore user which by default is null
    var firestoreUser: User? by mutableStateOf(null)

    /**
     * addUserHelper - if the user doesn't exist it adds the user to the database
     * @param user - User object
     */
    private fun addUserHelper(user: User) = run {
        // id of the user which extracts from the first part of the gmail
        val userID = user.email.replace("@gmail.com", "")
        // creates a document with the userID in the collection
        collection.document(userID).set(user, SetOptions.merge())
            .addOnSuccessListener {
                firestoreUser = user
            }
            .addOnFailureListener { e ->
                throw Error.NotFoundException(e.message.toString())
            }
    }

    /**
     * getUserByID - get the user from the database
     * @param userId - id of the user
     */
    fun getUserById(userId: String) = run {
        // gets the user
        collection.document(userId)
            .get()
            .addOnSuccessListener { document ->
                // return if document does not exist
                if (!document.exists()) {
                    throw Error.NotFoundException(userId)
                }
                // stores to the user as an User Object
                val user = document.toObject<User>()
                // stores the user
                firestoreUser = user
                return@addOnSuccessListener
            }
            .addOnFailureListener { exception ->
                throw Error.NotFoundException(exception.message.toString())
            }
    }

    /**
     * addUser - Add the user to the database
     * @param user - User Object
     */
    fun addUser(user: User) = run {
        val userId = user.email.replace("@gmail.com", "")
        // asks for the user from the database
        collection.document(userId).get()
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    throw Error.NotFoundException(userId)
                }
                // if the task is successful it saves the result
                val document = task.result

                if (document != null) {
                    // if the user does not exist on database
                    if (!document.exists()) {
                        // we call the addUserHelper and pass the user to it
                        addUserHelper(user)
                    } else {
                        // save the instance of the user if user exists
                        firestoreUser = user
                    }
                }
            }
    }

    /**
     * updateUserByID - updates the user's information by the id of the user
     * @param userId - Id of the user
     * @param updatedUser - A user object with the newest information
     */
    fun updateUserByID(
        userId: String,
        updatedUser: User
    ) {
        // updates the document with the user's id
        collection.document(userId).update(
            // maps the new info to the user's fields
            mapOf(
                "username" to updatedUser.username,
                "snapchat" to updatedUser.snapchat,
                "instagram" to updatedUser.instagram,
                "phone" to updatedUser.phone,
                "discord" to updatedUser.discord
            )
        ).addOnFailureListener{
            throw Error.NotFoundException(userId)
        }
    }
}