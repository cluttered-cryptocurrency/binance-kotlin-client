package com.cluttered.cryptocurrency.model.withdraw

enum class WithdrawStatus {
    EMAIL_SENT,
    CANCELLED,
    AWAITING_APPROVAL,
    REJECTED,
    PROCESSING,
    FAILURE,
    COMPLETED;

    companion object {
        private val values: Array<WithdrawStatus> by lazy {
            WithdrawStatus.values()
        }

        @JvmStatic
        fun fromOrdinal(ordinal: Int): WithdrawStatus {
            return values[ordinal]
        }
    }

    override fun toString(): String {
        return ordinal.toString()
    }
}