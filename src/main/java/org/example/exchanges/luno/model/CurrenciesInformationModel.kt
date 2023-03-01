package org.example.exchanges.luno.model

import org.example.utils.RoundToMinQty
import java.math.BigDecimal
import java.util.Currency

data class CurrenciesInformationModel(
        val symbol: String,
        val baseCurrency: String,
        val quoteCurrency: String,
        val amountLength: BigDecimal
)
