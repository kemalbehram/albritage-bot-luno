package org.example.exchanges.luno.dto

data class GetFullOrderBookDto(
        val timestamp: Long,
        val asks: List<Ask>,
        val bids: List<Ask>
) {
    data class Ask (
            val price: String,
            val volume: String
    )
}
