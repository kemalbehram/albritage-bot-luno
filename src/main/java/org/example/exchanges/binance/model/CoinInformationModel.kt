package org.example.exchanges.binance.model

import java.math.BigDecimal

data class CoinInformationModel(
        val symbol: String,
        val baseSymbol: String,
        val quoteSymbol: String,
        val filters: LotSize
) {
    data class LotSize(
            val minQty: BigDecimal,
            val maxQty: BigDecimal,
            val stepSize: BigDecimal
    )
}
