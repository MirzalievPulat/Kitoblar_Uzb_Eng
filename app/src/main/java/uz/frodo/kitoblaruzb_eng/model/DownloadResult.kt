package uz.frodo.kitoblaruzb_eng.model

import java.io.File

sealed interface DownloadResult {
    data class Success(val filePath:File):DownloadResult
    data class Fail(val message:String):DownloadResult
    data class Progress(val percent:Int):DownloadResult
}