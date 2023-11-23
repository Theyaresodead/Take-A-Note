package com.example.takeanote.components


import android.view.View.OnClickListener
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteInputField(
    modifier: Modifier =Modifier,
    text: String,
    label : String,
    ml:Int=1,
    onTextChange:(String)->Unit,
    imeAction: () -> Unit={}
){


    val keyboardController=LocalSoftwareKeyboardController.current
    TextField(value = text, onValueChange = onTextChange,colors =TextFieldDefaults.textFieldColors(
        backgroundColor = Color.Transparent), maxLines = ml,
    label = { Text(text = label)},
    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
    keyboardActions = KeyboardActions(onDone = {
       imeAction()
     keyboardController?.hide()
     }
    ), modifier = modifier
    )
}


@Composable
fun NoteButton(modifier: Modifier=Modifier,text: String,onClick:()->Unit,
enable:Boolean=true){
    Button(onClick = onClick, shape = RoundedCornerShape(5.dp), enabled = enable, modifier = modifier) {
        Text(text = text)
    }

}