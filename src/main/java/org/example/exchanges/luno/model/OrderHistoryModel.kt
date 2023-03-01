package org.example.exchanges.luno.model

import org.example.domain.enums.ExchangeStates
import org.example.domain.enums.OrderSide
import java.math.BigDecimal
import java.sql.Timestamp

data class OrderHistoryModel(
        val symbol: String,
        val state: ExchangeStates,
        val orderId: String,
        val timestamp: Long,
        val fee: BigDecimal,
        val type: OrderSide
)