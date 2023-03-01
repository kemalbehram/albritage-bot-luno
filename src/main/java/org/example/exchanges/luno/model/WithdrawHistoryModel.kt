package org.example.exchanges.luno.model

import org.example.domain.enums.ExchangeStates
import java.sql.Timestamp

data class WithdrawHistoryModel(
        val currency: String,
        val id: String,
        val amount: Double,
        val fee: Double,
        val state: ExchangeStates,
        val timestamp: Long
)
