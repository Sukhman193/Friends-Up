package ca.finalfive.friendsup.repositories

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ca.finalfive.friendsup.helpers.Error
import ca.finalfive.friendsup.models.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await


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
class FirestoreUserRepository {
    // get the instance of the firestore
    private val firestore = FirebaseFirestore.getInstance()

    // get the collection of the Users
    private val collection = firestore.collection(Constants.USERS)

    // friend user which by default is null
    var friend: User? by mutableStateOf(null)

    // firestore user which by default is null
    var firestoreUser: User? by mutableStateOf(null)

    /**
     * addUserHelper - if the user doesn't exist it adds the user to the database
     * @param user - User object
     */
    private suspend fun addUserHelper(user: User) {
        // id of the user which extracts from the first part of the gmail
        val userID = user.email.replace("@gmail.com", "")
        // creates a document with the userID in the collection
        collection.document(userID).set(user, SetOptions.merge()).await()
    }

    /**
     * getUserByID - get the user from the database
     * @param userId - id of the user
     */
    suspend fun getUserById(userId: String) {
        // gets the user
        val document = collection.document(userId).get().await()
        // return if document does not exist
        if (!document.exists()) {
            throw Error.NotFoundException(userId)
        }
        Log.d("LLAMA doc", document.toString())
        // stores to the user as an User Object
        val user = document.toObject<User>()
        // stores the user
        firestoreUser = user
        Log.d("LLAMA", firestoreUser?.email.toString())
    }

    /**
     * addUser - Add the user to the database
     * @param user - User Object
     */
    suspend fun addUser(user: User) {
        val userId = user.email.replace("@gmail.com", "")
        // asks for the user from the database
        val document = collection.document(userId).get().await()
        // if the user does not exist on database
        if (!document.exists()) {
            // we call the addUserHelper and pass the user to it
            addUserHelper(user)
        }
    }

    /**
     * updateUserByID - updates the user's information by the id of the user
     * @param userId - Id of the user
     * @param updatedUser - A user object with the newest information
     */
    suspend fun updateUserByID(
        userId: String,
        updatedUser: User
    ) {
        // updates the document with the user's id
        collection.document(userId).set(
            // maps the new info to the user's fields
            mapOf(
                "username" to updatedUser.username,
                "snapchat" to updatedUser.snapchat,
                "instagram" to updatedUser.instagram,
                "phone" to updatedUser.phone,
                "discord" to updatedUser.discord
            )
        ).await()
    }

    /**
     * findFriendById - finds the friend of the user
     * @param userId - id of the user's friend
     */
    suspend fun findFriendById(userId: String) {
        // gets the user
        val document = collection.document(userId)
        // start a transaction
        firestore.runTransaction { transaction ->
            // Access to the friend's field
            val snapshot = transaction.get(document)
            // retrieve the user object
            val userFound = snapshot.toObject<User>()
            // update the friend
            friend = userFound
            // check to see if the user has been found
            if (friend == null) {
                throw Exception("User Was Not Found")
            }
        }.await()
        Log.d("LLAMA", friend?.email.toString())
    }

    /**
     * deleteFriend - deletes the friend from the user and vice versa
     */
    suspend fun deleteFriend() {
        // if the friend is null throw an error
        if (friend == null) {
            throw Exception("The Friend does not exist")
        }
        // if the user is null throw an error
        if (firestoreUser == null) {
            throw Exception("The User has been deleted")
        }
        // updated user object
        val updateUser = firestoreUser!!
        // updated friend object
        val updateFriend = friend!!

        // returns a list that does not have the user's id in it
        updateFriend.friendList = updateFriend.friendList.filter {
            !updateUser.email.startsWith("$it@")
        }
        // returns a list that does not have the friend's id in it
        updateUser.friendList = updateUser.friendList.filter {
            !updateFriend.email.startsWith("$it@")
        }
        // the current user's id
        val currentUserId = updateUser.email.replace("@gmail.com", "")
        // the friend's id
        val friendId = updateFriend.email.replace("@gmail.com", "")

        // updates the friend's database
        collection.document(friendId).set(
            mapOf(
                "friendList" to updateFriend.friendList
            )
        ).await()
        // updates the user's database
        collection.document(currentUserId).set(
            mapOf(
                "friendList" to updateUser.friendList
            )
        ).await()
    }
}