package uz.polat.kitoblaruzb_eng.model

import androidx.annotation.Keep
import java.io.File

@Keep
sealed interface DownloadResult {
    data class Success(val filePath:File):DownloadResult
    data class Fail(val message:String):DownloadResult
    data class Progress(val percent:Int):DownloadResult
}