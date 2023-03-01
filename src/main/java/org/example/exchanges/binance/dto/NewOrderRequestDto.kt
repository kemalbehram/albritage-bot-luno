package org.example.exchanges.binance.dto

data class NewOrderRequestDto(
        val symbol: String,
        val side: String,
        val type: String,
        val quantity: String
)
