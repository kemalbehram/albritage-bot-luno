package org.example.exchanges.luno.model

data class OrderModel(
        val symbol: String,
        val id: String,
        val timestamp: Long
)
