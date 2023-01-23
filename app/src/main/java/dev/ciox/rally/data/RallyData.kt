package dev.ciox.rally.data

import androidx.compose.ui.graphics.Color

data class Bill(
    val name: String,
    val due: String,
    val amount: Float,
    val color: Color
)

data class Account(
    val name: String,
    val number: Int,
    val balance: Float,
    val color: Color
)

object UserData {
    val bills: List<Bill> = listOf(
        Bill(
            "Sacco Payment",
            "Jan 29",
            4500.00f,
            Color(0xFFFFDC78)
        ),
        Bill(
            "Rent",
            "Feb 9",
            12000f,
            Color(0xFFFF6951)
        ),
        Bill(
            "House utility",
            "Feb 22",
            10000.00f,
            Color(0xFFFFD7D0)
        ),
        Bill(
            "NCBA Loan",
            "Feb 29",
            4000f,
            Color(0xFFFFAC12)
        ),
        Bill(
            "Internet & Utility",
            "Feb 29",
            7750.4f,
            Color(0xFFFFAC12)
        )
    )


    val accounts: List<Account> = listOf(
        Account(
            "Checking",
            1234,
            22150.13f,
            Color(0xFF004940)
        ),
        Account(
            "Home Savings",
            5678,
            867612.88f,
            Color(0xFF005D57)
        ),
        Account(
            "Car Savings",
            9012,
            987746.48f,
            Color(0xFF04B97F)
        ),
        Account(
            "Vacation",
            3456,
            25303.00f,
            Color(0xFF37EFBA)
        )
    )

    fun getAccount(accountName: String): Account {
        return accounts.first { it.name == accountName }
    }
}