package com.cluttered.cryptocurrency.model.withdraw

import com.google.gson.annotations.SerializedName

data class WithdrawRequest(
        val id: String,
        @SerializedName("msg") val message: String,
        val success: Boolean
)