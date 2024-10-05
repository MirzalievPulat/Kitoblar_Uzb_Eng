package uz.frodo.kitoblaruzb_eng.app

import android.app.Application
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import uz.frodo.kitoblaruzb_eng.repository.Repository
import javax.inject.Inject

@HiltAndroidApp
class App:Application() {



    override fun onCreate() {
        super.onCreate()
    }
}