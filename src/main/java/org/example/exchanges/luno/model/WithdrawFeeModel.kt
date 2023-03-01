package org.example.exchanges.luno.model

import java.math.BigDecimal

data class WithdrawFeeModel(
        val currency: String,
        val fee: BigDecimal
)
