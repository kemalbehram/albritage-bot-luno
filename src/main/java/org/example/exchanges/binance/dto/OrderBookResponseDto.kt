package org.example.exchanges.binance.dto

data class OrderBookResponseDto(
        val bids: List<List<String>>,
        val asks: List<List<String>>,
)
