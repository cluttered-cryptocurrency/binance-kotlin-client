package com.cluttered.cryptocurrency.model.withdraw

data class DepositAddress(
        val address: String,
        val success: Boolean,
        val addressTag: String,
        val asset: String
)