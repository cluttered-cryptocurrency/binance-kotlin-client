package com.cluttered.cryptocurrency.model.userstream

import com.google.gson.annotations.SerializedName

data class ListenKey(
        @SerializedName("listenKey") val key: String
)