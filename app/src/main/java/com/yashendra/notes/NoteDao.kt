package com.yashendra.notes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)
//suspend is used so that these function cant be called by directly and to
    //insure that it will run in the background
    //suspend----->coroutine
    @Delete
    suspend fun delete(note: Note)

    @Query("select * from notes_table order by id asc")
    fun getAllNotes():LiveData<List<Note>>
    //livedata-so that whenever a change is made in the data it can be observed by activity

}