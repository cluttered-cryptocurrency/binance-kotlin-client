package com.cluttered.cryptocurrency.model.withdraw

enum class WithdrawStatus(private val display: String) {
    PENDING("0"),
    SUCCESS("1");

    override fun toString(): String {
        return display
    }
}