package com.example.takeanote.NoteScreen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.takeanote.data.NoteDataSource
import com.example.takeanote.model.Note
import com.example.takeanote.respository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
//injecting the repository into the view model
@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NotesRepository): ViewModel() {
    private val _notelist=MutableStateFlow<List<Note>>(emptyList())
    var notelist=_notelist.asStateFlow()
   // var notelist = mutableStateListOf<Note>()
    //intialising the notelist with notes
    init {
        //Dispatcher.IO means the task written after it will be launched or provided with multiple threads.
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged().collect{ listOfNotes ->
                if(listOfNotes.isNullOrEmpty())
                {
                    Log.d("Empty" ,"Empty List")
                }
                else{
                    _notelist.value=listOfNotes
                }
            }
        }
        //notelist.addAll(NoteDataSource().loadNotes())
    }

    //  function to add a note
 fun addNote(note:Note) = viewModelScope.launch { repository.addNote(note) }
    //function to remove note
    fun removeNote(note :Note) =viewModelScope.launch { repository.deleteNote(note) }
    fun updateNote(note: Note)= viewModelScope.launch { repository.updateNote(note) }

}