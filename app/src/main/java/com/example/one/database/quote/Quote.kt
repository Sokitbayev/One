package com.example.one.database.quote

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
@Entity(tableName = "quote_table")
data class Quote(
    @ColumnInfo(name = "quote_text")
    @SerializedName("quoteText")
    val quoteText: String)
