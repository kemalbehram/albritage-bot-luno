package org.example.exchanges.luno.model

import java.math.BigDecimal
import java.util.Currency

data class BalanceModel(
        val currency: String,
        val balance: BigDecimal
)
