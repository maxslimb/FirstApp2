package com.example.firstapp2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "colors")
data class Color(
   @PrimaryKey(autoGenerate = true)
   val _id:Int,

   @ColumnInfo(name = "hex_color")
   val hex: String,

   val name: String
)