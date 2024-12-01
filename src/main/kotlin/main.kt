fun main() {
    val cardType = "Mastercard"
    val previousTransfersThisMonth = 80_000
    val transferAmount = 10_000

    calcCommission(cardType, previousTransfersThisMonth, transferAmount)
}

fun calcCommission(cardType: String = "Мир", previousTransfersThisMonth: Int = 0, transferAmount: Int) {

    if (transferAmount > 150_000) {
        println("Ошибка: превышен суточный лимит перевода (150 000 руб.)")
        return
    }

    val totalTransfersThisMonth = previousTransfersThisMonth + transferAmount
    if (totalTransfersThisMonth > 600_000) {
        println("Ошибка: превышен месячный лимит перевода (600 000 руб.)")
        return
    }

    val commission = when (cardType) {
        "Mastercard" -> {
            if (totalTransfersThisMonth <= 75_000) {
                0 // Комиссия не взимается, если лимит не превышен
            } else {
                val excessAmount = totalTransfersThisMonth - 75_000
                (excessAmount * 0.006 + 20).toInt()
            }
        }
        "Visa" -> {
            val commissionAmount = (transferAmount * 0.0075).toInt()
            if (commissionAmount < 35) 35 else commissionAmount
        }
        "Мир" -> {
            0 // Комиссия не взимается
        }
        else -> {
            println("Ошибка: неизвестный тип карты")
            return
        }
    }

    if (commission > 0) {
        println("Комиссия: ${commission} рублей")
    } else {
        println("Комиссия не взимается")
    }
}