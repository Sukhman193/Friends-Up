package ca.finalfive.strangercommons.repositories

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ca.finalfive.strangercommons.models.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase

class Constants{
    companion object{
        const val USERS = "Users"
    }
}

class FirestoreUserRepository(){
    private val firestore = FirebaseFirestore.getInstance()
    val collection = firestore.collection(Constants.USERS)
    var firestoreUser: User? by mutableStateOf(null)

    fun addUserHelper(user: User) = run {
//        val collection = firestore.collection(Constants.USERS)
        val userID = user.email.replace("@gmail.com","")

        collection.document(userID).set(user, SetOptions.merge()).addOnSuccessListener { documentReference ->
            firestoreUser = user
            Log.d("LLAMA", "DocumentSnapshot written: $documentReference")
        }
            .addOnFailureListener { e ->
                Log.w("LLAMA", "Error adding document", e)
            }
    }

    fun getUserById(userId: String)= run {
//        val collection = firestore.collection(Constants.USERS)
        collection.document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val user = User(
                        email = document.getString("email")!!,
                        username = document.getString("username")!!,
                        snapchat = document.getString("snapchat")!!,
                        instagram = document.getString("instagram")!!,
                        discord = document.getString("discord")!!,
                        phone = document.getString("phone")!!
                    )
                    firestoreUser = user
                    Log.d("LLAMA", firestoreUser.toString())
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    return@addOnSuccessListener
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }

    fun addUser(user: User) = run {
        collection.document(user.email.replace("@gmail.com","")).get()
            .addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    val document = task.result
                    if (document != null){
                        if (!document.exists()){
                            addUserHelper(user)
//                            Log.d("TAG", "Document already exists.")
                        } else{
                            firestoreUser = user
                            Log.d("TAG", "Document doesn't exist.")
                        }
                    }
                } else{
                    Log.d("TAG", "Error: ", task.exception)
                }
            }
    }


    fun getAllUsers(){

    }

    fun deleteUser(){

    }
    fun updateUserByID(){

    }
}