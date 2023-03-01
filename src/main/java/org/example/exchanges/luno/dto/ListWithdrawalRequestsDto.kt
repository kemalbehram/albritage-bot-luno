package org.example.exchanges.luno.dto

data class ListWithdrawalRequestsDto(
        val withdrawals: List<Withdrawal>
) {
    data class Withdrawal (
            val amount: String,
            val created_at: String,
            val currency: String,
            val external_id: String,
            val fee: String,
            val id: String,
            val status: String,
            val type: String
    )
}
