package org.example.exchanges.binance.model

import org.example.domain.enums.ExchangeStates
import java.sql.Timestamp

data class WithdrawHistoryModel(
        val id: String,
        val amount: Double,
        val fee: Double,
        val state: ExchangeStates,
        val timestamp: Long
)
