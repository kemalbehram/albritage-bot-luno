package org.example.exchanges.luno.dto

data class GetBalanceDto(
        val balance: List<Balance>
) {
    data class Balance (
            val account_id: String,
            val asset: String,
            val balance: String,
            val reserved: String,
            val unconfirmed: String
    )

}
