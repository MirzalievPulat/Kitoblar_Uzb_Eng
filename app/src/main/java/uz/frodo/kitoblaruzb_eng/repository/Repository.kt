package uz.frodo.kitoblaruzb_eng.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.frodo.kitoblaruzb_eng.model.Book
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor() {
    private val firestore = Firebase.firestore

    fun getBooks(): Flow<Result<List<Book>>> = callbackFlow {
        firestore.collection("Books")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val list = ArrayList<Book>()
                querySnapshot.forEach {
                    list.add(it.toObject(Book::class.java))
                }
                trySend(Result.success(list))
            }
            .addOnFailureListener { trySend(Result.failure(it)) }

        awaitClose()
    }

    fun getCategories(category: String):Flow<Result<List<Book>>> = callbackFlow {
        println("repository category: $category")
        firestore.collection("Books")
            .whereArrayContains("genre",category)
            .get()
            .addOnSuccessListener {querySnapshot->
                val list = ArrayList<Book>()
                querySnapshot.forEach {
                    list.add(it.toObject(Book::class.java))
                }
                trySend(Result.success(list))
            }
            .addOnFailureListener { trySend(Result.failure(it)) }

        awaitClose()
    }
}