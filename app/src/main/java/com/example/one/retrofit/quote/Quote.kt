package com.example.one.retrofit.quote

import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("quoteText")
    val quoteText: String)
