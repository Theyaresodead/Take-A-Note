package com.example.takeanote.di

import android.content.Context
import androidx.room.Room
import com.example.takeanote.data.NoteDataBase
import com.example.takeanote.data.NoteDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// serves as adding various dependencies to hilt for binding and for instantiating and creating the database.

@InstallIn(SingletonComponent::class)  // It means it is created only once.
@Module
object AppModule {

    @Singleton
    @Provides
    // provides means this is available to all the realm of dependencies
    fun provideNotesDao(noteDataBase: NoteDataBase) :NoteDatabaseDao= noteDataBase.noteDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) : NoteDataBase
    = Room.databaseBuilder(
        context,
        NoteDataBase::class.java,
        "notes_db").fallbackToDestructiveMigration().build()

}