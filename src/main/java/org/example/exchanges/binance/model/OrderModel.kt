package org.example.exchanges.binance.model

data class OrderModel(
        val symbol: String,
        val id: String,
        val timestamp: Long
)
