package ca.finalfive.strangercommons.repositories

import android.content.ContentValues.TAG
import android.util.Log
import ca.finalfive.strangercommons.models.User
import com.google.firebase.firestore.FirebaseFirestore

class Constants{
    companion object{
        const val USERS = "Users"
    }
}

class FirestoreUserRepository {
    private val firestore = FirebaseFirestore.getInstance()
    var firestoreUser: User? = null

    fun addUser(user: User) = run {
        val collection = firestore.collection(Constants.USERS)
        val userID = user.email.replace("@gmail.com","")

        collection.document(userID).set(user).addOnSuccessListener { documentReference ->
            firestoreUser = user
            Log.d("LLAMA", "DocumentSnapshot written: $documentReference")
        }
            .addOnFailureListener { e ->
                Log.w("LLAMA", "Error adding document", e)
            }
    }

    fun getUserById(userId: String){
        val collection = firestore.collection(Constants.USERS)
        collection.document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val user = User(
                        email = document.getString("email")!!,
                        username = document.getString("username"),
                        snapchat = document.getString("snapchat"),
                        instagram = document.getString("instagram"),
                        phone = document.getString("phone")
                    )
                    firestoreUser = user
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }

    fun getAllUsers(){

    }

    fun deleteUser(){

    }
    fun updateUserByID(){

    }
}