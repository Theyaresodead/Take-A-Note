package com.example.takeanote.NoteScreen

import android.annotation.SuppressLint
import android.graphics.Paint.Align
import android.provider.ContactsContract
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.takeanote.R
import com.example.takeanote.components.NoteButton
import com.example.takeanote.components.NoteInputField
import com.example.takeanote.data.NoteDataSource
import com.example.takeanote.model.Note
import com.example.takeanote.util.formatDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ofPattern
import kotlin.text.Typography


@SuppressLint("ResourceType", "SuspiciousIndentation")
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note)->Unit,
    onRemoveNote: (Note)->Unit
){
    var t by remember {
        mutableStateOf("")
    }
    var des by remember {
        mutableStateOf("")
    }
    val cont = LocalContext.current
        Column(modifier = Modifier
            .padding(6.dp)
            .fillMaxHeight()
            .fillMaxWidth()) {
            TopAppBar(title = {
                             Text(text = stringResource(id = R.string.app_name))
            }, actions = {
                Icon(imageVector = Icons.Default.Notifications, contentDescription ="Icon" )
            }, backgroundColor = Color.LightGray
            )
             Spacer(modifier = Modifier.height(15.dp))
            // Main Content
            Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
               NoteInputField(text = t, label = "Title", onTextChange = {
                   if(it.all{ char ->
                           char.isLetter() || char.isWhitespace()
                       }) t=it
               } )
                Spacer(modifier = Modifier.height(10.dp))
                NoteInputField(text = des, label = "Description" , onTextChange = {
                    if(it.all{ char ->
                            char.isLetter() || char.isWhitespace()
                        }) des=it
                })
                Spacer(modifier = Modifier.height(10.dp))
                NoteButton(text = "SAVE", onClick = {
                    if(t.isNotEmpty() || des.isNotEmpty())
                    {   onAddNote(Note(t=t,des=des))
                        t=""
                        des=""
                        Toast.makeText(cont,"Note Added",Toast.LENGTH_SHORT).show()
                    }
                })
                Divider(modifier = Modifier.padding(10.dp))
                LazyColumn{
                   items(notes){note->
                       NoteRow(note = note, onNoteClicked = {
                           onRemoveNote(note)
                           Toast.makeText(cont,"Note Removed",Toast.LENGTH_SHORT).show()
                       })
                       
                   }
                }
            }
        }
}

@Composable
fun NoteRow(modifier: Modifier=Modifier,
note :Note,onNoteClicked : (Note)->Unit){
    Surface(modifier = Modifier
        .padding(6.dp)
        .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
        .fillMaxWidth(),color=Color.LightGray) {
        
        Column(
            modifier
                .clickable { onNoteClicked(note) }
                .padding(vertical = 6.dp, horizontal = 14.dp),
        horizontalAlignment = Alignment.Start) {
            Text(text = note.t, style = MaterialTheme.typography.subtitle1)
            Text(text = note.des ,style=MaterialTheme.typography.subtitle2)
            Text(text = formatDate(note.dateandtime.time), style = MaterialTheme.typography.caption)

        }
    }
}



@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen(notes = NoteDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}