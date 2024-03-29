package com.avility.domain.model

data class MovementModel(
    var id: String? = null,
    val date: String,
    val accumulatedPoints: Int,
    val numberInstallments: Int,
    val transactionDescription: String,
    val rateTaxPercent: String,
    val transactionValue: String,
    val userId: String,
    val natureTransaction: Boolean
) {
    companion object {
        fun fromMap(id: String, data: Map<String, Any>): MovementModel = MovementModel(
            id = id,
            date = data["date"].toString(),
            accumulatedPoints = data["accumulatedPoints"].toString().toInt(),
            numberInstallments = data["numberInstallments"].toString().toInt(),
            transactionDescription = data["transactionDescription"].toString(),
            rateTaxPercent = data["rateTaxPercent"].toString(),
            transactionValue = data["transactionValue"].toString(),
            userId = data["userId"].toString(),
            natureTransaction = data["natureTransaction"].toString().toBoolean()
        )
    }
}
