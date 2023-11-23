package com.example.takeanote.data

import com.example.takeanote.model.Note

class NoteDataSource{
    fun loadNotes(): List<Note>
    {
        return listOf(
            Note(t = "hi" , des = "hello")
        )
    }
}