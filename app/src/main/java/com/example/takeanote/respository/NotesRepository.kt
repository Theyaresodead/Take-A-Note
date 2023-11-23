package com.example.takeanote.respository

import com.example.takeanote.data.NoteDatabaseDao
import com.example.takeanote.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

// inject is used here to add a property to the constructor.
class NotesRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {
    suspend fun addNote(note:Note)=noteDatabaseDao.insert(note = note)
    suspend fun updateNote(note:Note)=noteDatabaseDao.update(note)
    suspend fun deleteNote(note:Note)=noteDatabaseDao.delete(note)
    suspend fun deleteAllNotes()=noteDatabaseDao.deleteAll()
    fun getAllNotes() : Flow<List<Note>> =noteDatabaseDao.getNotes().flowOn(Dispatchers.IO).conflate()
}