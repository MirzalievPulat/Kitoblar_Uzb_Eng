package uz.frodo.kitoblaruzb_eng.repository

import android.content.Context
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import uz.frodo.kitoblaruzb_eng.app.App
import uz.frodo.kitoblaruzb_eng.model.Book
import uz.frodo.kitoblaruzb_eng.model.DownloadResult
import uz.frodo.kitoblaruzb_eng.repository.room.BookDao
import uz.frodo.kitoblaruzb_eng.repository.room.BookEntity
import uz.frodo.kitoblaruzb_eng.repository.shared.LocalStorage
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    @ApplicationContext private val applicationContext: Context,
    private val bookDao: BookDao,
) {


    private val firestore = Firebase.firestore
    private val storage = Firebase.storage.reference

    fun getBooks(): Flow<Result<List<Book>>> = callbackFlow<Result<List<Book>>> {
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
    }.catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)

    fun getCategories(category: String):Flow<Result<List<Book>>> = callbackFlow<Result<List<Book>>> {
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
    }.catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)

    fun downloadBook(url:String):Flow<DownloadResult> = callbackFlow {
        Log.d("TAG", "downloadBook: url: $url")
        val directory = File(applicationContext.filesDir, "/books")
        if (!directory.exists()) {
            directory.mkdirs()
        }

        val filePath = File(directory,url.substringAfterLast("/"))
        val isCreated = filePath.createNewFile()

        Log.d("TAG", "downloadBook: isCreated: $isCreated")

        val reference = FirebaseStorage.getInstance().getReferenceFromUrl(url)
        reference
            .getFile(filePath)
            .addOnSuccessListener { trySend(DownloadResult.Success(filePath)); close() }
            .addOnFailureListener { trySend(DownloadResult.Fail(it.message?:"")) ; close()}
            .addOnProgressListener { trySend(DownloadResult.Progress((it.bytesTransferred*100/it.totalByteCount).toInt())) }

        awaitClose()
    }.catch { emit(DownloadResult.Fail(it.message?:"")) }.flowOn(Dispatchers.IO)


    fun insertBook(bookEntity: BookEntity){
        bookDao.insertBook(bookEntity)
    }

    fun getDownloadedBooks() = bookDao.getAllBooks()

}