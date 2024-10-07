package uz.frodo.kitoblaruzb_eng.model

import android.os.Parcelable
import java.io.Serializable

//@Parcelize
data class Book(
    val id:Long = 0,
    val author: String = "",
    val name: String = "",
    val pages: Int = 0,
    val path: String = "",
    val image:String = "",
    val rating:Float = 0.0f,
    val summary:String = "",
    val language:String = "English",
    val genre:List<String> = emptyList(),
):Serializable
