package com.example.takeanote.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.takeanote.model.Note
import com.example.takeanote.util.DateConverter
import com.example.takeanote.util.UUIDConverter

// creating a database class

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class,UUIDConverter::class)
abstract class NoteDataBase: RoomDatabase(){
    // creating a function that will give us DAO(Data Access Object)
     abstract fun noteDao(): NoteDatabaseDao
}