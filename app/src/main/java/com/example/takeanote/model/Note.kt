package com.example.takeanote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

// Converting these values in to entities
@Entity(tableName = "notes_tbl")
data class Note(
    // making id as the primary key of the table
    @PrimaryKey
    var id:UUID= UUID.randomUUID(),
    @ColumnInfo(name = "Column_title")
    var t :String,
    @ColumnInfo(name = "Column_description")
    var des :String,
    @ColumnInfo(name = "Column_Entry")
    var dateandtime: Date = Date.from(Instant.now()) as Date
)
