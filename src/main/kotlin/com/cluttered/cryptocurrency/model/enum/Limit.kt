package com.cluttered.cryptocurrency.model.enum

enum class Limit(private val value: Int) {
    FIVE(5),
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    ONE_HUNDRED(100),
    FIVE_HUNDRED(500),
    ONE_THOUSAND(1000);

    override fun toString(): String {
        return value.toString()
    }
}