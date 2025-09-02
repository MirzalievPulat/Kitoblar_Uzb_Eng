package uz.polat.kitoblaruzb_eng.utils

import android.os.Bundle
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.crashlytics
import timber.log.Timber


//timber
fun logTimber(message: String, tag: String = "TTT"){
    Timber.tag(tag).d(message)
}


//crashlytics
fun logCrashlytics(message: String){
    FirebaseCrashlytics.getInstance().log(message)
}

fun logExceptionCrashlytics(exception: Exception){
    FirebaseCrashlytics.getInstance().recordException(exception)
}

fun setUserIdCrashlytics(id: String){
    FirebaseCrashlytics.getInstance().setUserId(id)
}

fun setCustomKeyCrashlytics(key: String, value: String){
    FirebaseCrashlytics.getInstance().setCustomKey(key,value)
}

fun collectionEnabledCrashlytics(enabled: Boolean){
    Firebase.crashlytics.isCrashlyticsCollectionEnabled = enabled
}


//analytics

fun logEventAnalytics(event: String,bundle: Bundle){
    Firebase.analytics.logEvent(event,bundle)
}


