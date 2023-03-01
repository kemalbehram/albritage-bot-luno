package org.example.exchanges.binance.model

import org.example.domain.enums.ExchangeStates
import org.example.domain.enums.OrderSide
import java.sql.Timestamp

data class OrderHistoryModel(
        val symbol: String,
        val orderId: String,
        val state: ExchangeStates,
        val side: OrderSide,
        val timestamp: Long
)