package org.example.exchanges.luno.dto

data class GetReceiveAddressDto(
        val asset: String,
        val address: String,
        val name: String,
        val account_id: String,
        val assigned_at: Long,
        val total_received: String,
        val total_unconfirmed: String,
        val receive_fee: String,
        val address_meta: List<AddressMeta>,
        val qr_code_uri: String
) {
    data class AddressMeta (
            val label: String,
            val value: String
    )
}
