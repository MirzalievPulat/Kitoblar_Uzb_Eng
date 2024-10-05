package uz.frodo.kitoblaruzb_eng.utils

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

fun <T> Flow<Result<T>>.onSuccess(action: suspend (T) -> Unit): Flow<Result<T>> =
    onEach { result ->
        result.onSuccess { action(it) }
    }
fun <T> Flow<Result<T>>.onFailure(action: suspend (Throwable) -> Unit): Flow<Result<T>> =
    onEach { result ->
        result.onFailure { action(it) }
    }

fun Context.showToast(message:String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}