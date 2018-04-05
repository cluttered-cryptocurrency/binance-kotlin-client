package com.cluttered.cryptocurrency.model.enums

enum class ChartInterval constructor(private val display: String) {
    MINUTES_1("1m"),
    MINUTES_3("3m"),
    MINUTES_5("5m"),
    MINUTES_15("15m"),
    MINUTES_30("30m"),
    HOURS_1("1h"),
    HOURS_2("2h"),
    HOURS_4("4h"),
    HOURS_6("6h"),
    HOURS_8("8h"),
    HOURS_12("12h"),
    DAYS_1("1d"),
    DAYS_3("3d"),
    WEEKS_1("1w"),
    MONTHS_1("1M");

    override fun toString(): String {
        return display
    }
}