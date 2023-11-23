package com.example.takeanote

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


// inherting Application means it inherits the entire applications
// Adding hiltandroidapp means now hilt can provide dependencies to all part of the application
@HiltAndroidApp
class NoteApplication :Application() {
}