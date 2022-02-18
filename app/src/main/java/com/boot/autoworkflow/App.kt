package com.boot.autoworkflow

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if(hasJunit()) error("!!!Alert!!! Has junit in the build")
    }
}