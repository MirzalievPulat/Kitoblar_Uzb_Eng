package uz.frodo.kitoblaruzb_eng.repository.shared

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class LocalStorage @Inject constructor(@ApplicationContext context: Context) : SharedPreferenceHelper(context) {

//    var token: String by strings("")
    var isDarkMode: Boolean by booleans(false)



}

