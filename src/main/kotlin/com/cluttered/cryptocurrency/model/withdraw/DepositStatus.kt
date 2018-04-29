package com.cluttered.cryptocurrency.model.withdraw

enum class DepositStatus {
    PENDING,
    SUCCESS;

    companion object {
        private val values: Array<DepositStatus> by lazy {
            DepositStatus.values()
        }

        @JvmStatic
        fun fromOrdinal(ordinal: Int): DepositStatus {
            return values[ordinal]
        }
    }

    override fun toString(): String {
        return ordinal.toString()
    }
}