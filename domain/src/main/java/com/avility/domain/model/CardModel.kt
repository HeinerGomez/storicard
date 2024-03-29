package com.avility.domain.model

data class CardModel(
    var id: String? = null,
    val ccv: Int,
    val cardNumber: String,
    val holderName: String,
    val monthExpire: String,
    val yearExpire: String,
    val userId: String,
) {
    companion object {
        fun fromMap(id: String, data: Map<String, Any>): CardModel = CardModel(
            id = id,
            ccv = data["ccv"].toString().toInt(),
            cardNumber = data["cardNumber"].toString(),
            holderName = data["holderName"].toString(),
            monthExpire = data["monthExpire"].toString(),
            yearExpire = data["yearExpire"].toString(),
            userId = data["userId"].toString(),
        )
    }
}
