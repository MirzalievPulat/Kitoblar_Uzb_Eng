package uz.frodo.kitoblaruzb_eng.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val filePath:String,
)
