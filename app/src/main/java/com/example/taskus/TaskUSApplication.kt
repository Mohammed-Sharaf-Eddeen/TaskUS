package com.example.taskus

import android.app.Application

class TaskUSApplication: Application() {
    /*
    The repository have to get the ApplicationContext, yes it can ask for it in other phases
    by the method of getApplicationContext(), and this would be totally okay. The only advantage
    of giving the repository the applicationContext here is that we will be creating the repository
    at the very beginning of the application even before onCreate() of the MainActvity class.
    Other Activities and fragments get created and destroyed many times for the rotations and
    the user surfing purposes, but the application context is only created once, and get destroyed
    once at the very end of the application. It serves as the global container of everything from
    activities and fragments. Look at the AndroidManifest file
    <application
        android:name=".TaskUSApplication"
        ......>
        <activity android:name=".MainActivity">
            ....
        </activity>
    </application>
     */
    override fun onCreate() {
        super.onCreate()
        TaskRepository.initialize(this)
    }
}