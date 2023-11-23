package com.example.takeanote.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.takeanote.model.Note
import kotlinx.coroutines.flow.Flow

// creating Dao to access the database ie to perform CRUD functions on the database.
@Dao
interface NoteDatabaseDao {

    @Query("SELECT *from notes_tbl")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * from notes_tbl where id=:id")
    suspend fun getNotebyId(id:String): Note

    // onconflict works that if there is any issue regarding a note in the table the it might take the actions specified.
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE) suspend fun update(note:Note)

    @Query("DELETE from notes_tbl")
   suspend fun deleteAll()

    @Delete
    suspend fun delete(note:Note)

}
