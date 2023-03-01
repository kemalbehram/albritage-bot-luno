package org.example.exchanges.luno.dto

data class TickersForAllCurrenciesDto(
        val tickers: List<Ticker>
) {
    data class Ticker (
            val pair: String,
            val timestamp: Long,
            val bid: String,
            val ask: String,
            val last_trade: String,
            val rolling_24_hour_volume: String,
            val status: String
    )
}
