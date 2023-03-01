package org.example.exchanges.binance.model

import org.example.domain.enums.ExchangeStates
import java.sql.Timestamp

data class DepositHistoryModel(
        val id: String,
        val amount: Double,
        val coin: String,
        val states: ExchangeStates,
        val timestamp: Long
)
