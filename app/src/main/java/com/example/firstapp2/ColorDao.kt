package com.example.firstapp2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface ColorDao {

    @Query("Select * from colors")
    fun getAll(): Array<Color>

    @Insert
    fun insert(vararg color: Color)

}