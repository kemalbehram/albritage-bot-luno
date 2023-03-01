package org.example.exchanges.binance.dto

data class NewOrderResponseDto(
        val symbol: String,
        val orderId: String,
        val orderListId: String,
        val clientOrderId: String,
        val transactTime: Long
)