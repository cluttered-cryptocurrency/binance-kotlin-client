package com.cluttered.cryptocurrency.model

enum class OrderStatus {
    NEW,
    PARTIALLY_FILLED,
    FILLED,
    CANCELED,
    PENDING_CANCEL, //currently unused
    REJECTED,
    EXPIRED
}