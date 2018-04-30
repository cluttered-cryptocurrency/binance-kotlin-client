package com.cluttered.cryptocurrency.model.withdraw

enum class SystemStatus {
    NORMAL,
    SYSTEM_MAINTENANCE;

    companion object {
        private val values: Array<SystemStatus> by lazy {
            SystemStatus.values()
        }

        @JvmStatic
        fun fromOrdinal(ordinal: Int): SystemStatus {
            return values[ordinal]
        }
    }

    override fun toString(): String {
        return ordinal.toString()
    }
}