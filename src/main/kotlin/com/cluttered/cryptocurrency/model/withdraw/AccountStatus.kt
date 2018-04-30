package com.cluttered.cryptocurrency.model.withdraw

import com.google.gson.annotations.SerializedName

data class AccountStatus(
        @SerializedName("msg") val message: String,
        val success: Boolean,
        @SerializedName("objs") val objects: List<String>
)