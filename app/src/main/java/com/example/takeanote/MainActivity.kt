package com.example.takeanote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.takeanote.NoteScreen.NoteScreen
import com.example.takeanote.NoteScreen.NoteViewModel
import com.example.takeanote.data.NoteDataSource
import com.example.takeanote.model.Note
import com.example.takeanote.ui.theme.TakeANoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
// This identifies as the dependency container
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TakeANoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   // val noteViewModel=viewModel<NoteViewModel>() // this also works fine
                    val noteViewModel:NoteViewModel by viewModels()
                    NotesApp(noteViewModel=noteViewModel)

                }
            }
        }
    }
}

@Composable
fun NotesApp(noteViewModel:NoteViewModel)
{
   val notelist=noteViewModel.notelist.collectAsState().value
    NoteScreen(notes = notelist , onAddNote = {
      noteViewModel.addNote(it)
    }, onRemoveNote = {
        noteViewModel.removeNote(it)
    })

}



@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TakeANoteTheme {
         NoteScreen(notes = NoteDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
    }
}