package org.example.exchanges.binance.dto

data class AllOrdersResponseDto(
        val symbol: String,
        val orderId: String,
        val orderListId: String,
        val clientOrderId: String,
        val price: Double,
        val origQty: Double,
        val executedQty: Double,
        val cummulativeQuoteQty: Double,
        val status: String,
        val timeInForce: String,
        val type: String,
        val side: String,
        val stopPrice: Double,
        val icebergQty: Double,
        val time: Long,
        val updateTime: Long,
        val isWorking: Boolean,
        val origQuoteOrderQty: Double,
        val workingTime: Long,
        val selfTradePreventionMode: String
)
