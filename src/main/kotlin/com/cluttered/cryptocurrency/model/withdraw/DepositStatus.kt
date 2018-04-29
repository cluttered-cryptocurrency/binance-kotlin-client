package com.cluttered.cryptocurrency.model.withdraw

enum class DepositStatus(private val display: String) {
    PENDING("0"),
    SUCCESS("1");

    override fun toString(): String {
        return display
    }
}