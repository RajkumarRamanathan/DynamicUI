package com.rajkumar.dynamic.mock.api

import com.rajkumar.dynamic.core.model.*
import kotlinx.serialization.json.*

object MockData {
    fun getHomeScreen(): Screen {
        return Screen(
            id = "home",
            title = "Good Morning, Rajkumar",
            content = Widget(
                type = "lazy_column",
                children = listOf(
                    Widget(
                        type = "balance_card",
                        properties = buildJsonObject {
                            put("balance", "₹1,24,500.00")
                            put("account_number", "**** 4589")
                            put("account_type", "Savings Account")
                        },
                        actions = mapOf(
                            "onClick" to Action(type = "navigate", payload = buildJsonObject { put("destination", "balance_details") })
                        )
                    ),
                    Widget(
                        type = "row",
                        properties = buildJsonObject { put("padding", 16) },
                        children = listOf(
                            createQuickAction("Send", "send_money"),
                            createQuickAction("Pay", "pay_bills"),
                            createQuickAction("Scan", "scan_qr"),
                            createQuickAction("Analytics", "analytics"),
                            createQuickAction("History", "transaction_history")
                        )
                    ),
                    Widget(
                        type = "text",
                        properties = buildJsonObject {
                            put("text", "Recent Transactions")
                            put("style", "titleLarge")
                            put("padding", 16)
                        }
                    ),
                    Widget(
                        type = "balance_card",
                        visibility = "is_premium==true",
                        animation = "expand",
                        properties = buildJsonObject {
                            put("balance", "₹5,00,000.00")
                            put("account_number", "**** 1122")
                            put("account_type", "Premium Wealth Account")
                        }
                    ),
                    createTransaction("Amazon India", "Shopping", "- ₹2,499.00", "20 Oct"),
                    createTransaction("HDFC Bank ATM", "Withdrawal", "- ₹5,000.00", "19 Oct"),
                    createTransaction("Salary Credit", "Income", "+ ₹85,000.00", "01 Oct")
                )
            )
        )
    }

    private fun createQuickAction(label: String, action: String) = Widget(
        type = "quick_action",
        properties = buildJsonObject { put("label", label) },
        actions = mapOf("onClick" to Action(type = "navigate", payload = buildJsonObject { put("destination", action) }))
    )

    fun getBalanceScreen(): Screen {
        return Screen(
            id = "balance",
            title = "Account Balance",
            content = Widget(
                type = "column",
                children = listOf(
                    Widget(
                        type = "balance_card",
                        properties = buildJsonObject {
                            put("balance", "₹1,24,500.00")
                            put("account_number", "**** 4589")
                            put("account_type", "Total Balance")
                        }
                    ),
                    Widget(
                        type = "text",
                        properties = buildJsonObject {
                            put("text", "Saving Goals")
                            put("style", "titleMedium")
                            put("padding", 16)
                        }
                    ),
                    createProgressItem("New Car", 0.65f, "₹8,50,000 / ₹12,00,000"),
                    createProgressItem("Europe Trip", 0.30f, "₹1,50,000 / ₹5,00,000")
                )
            )
        )
    }

    fun getStatementScreen(): Screen {
        return Screen(
            id = "statement",
            title = "E-Statement",
            content = Widget(
                type = "lazy_column",
                children = listOf(
                    createTransaction("Amazon India", "Shopping", "- ₹2,499.00", "20 Oct"),
                    createTransaction("HDFC Bank ATM", "Withdrawal", "- ₹5,000.00", "19 Oct"),
                    createTransaction("Salary Credit", "Income", "+ ₹85,000.00", "01 Oct"),
                    createTransaction("Zomato", "Food", "- ₹540.00", "30 Sep"),
                    createTransaction("Uber", "Transport", "- ₹210.00", "29 Sep"),
                    createTransaction("Netflix", "Subscription", "- ₹499.00", "28 Sep")
                )
            )
        )
    }

    fun getAnalyticsScreen(): Screen {
        return Screen(
            id = "analytics",
            title = "Spending Analytics",
            content = Widget(
                type = "column",
                children = listOf(
                    Widget(
                        type = "analytics_chart",
                        properties = buildJsonObject {
                            put("title", "Monthly Spends")
                            put("data", buildJsonArray {
                                add(1200f)
                                add(2500f)
                                add(1800f)
                                add(3200f)
                                add(2100f)
                                add(2800f)
                            })
                        }
                    ),
                    Widget(
                        type = "text",
                        properties = buildJsonObject {
                            put("text", "Top Categories")
                            put("style", "titleMedium")
                            put("padding", 16)
                        }
                    ),
                    createProgressItem("Shopping", 0.45f, "₹12,400"),
                    createProgressItem("Food & Drinks", 0.25f, "₹6,200"),
                    createProgressItem("Transport", 0.15f, "₹4,100")
                )
            )
        )
    }

    fun getPayBillsScreen(): Screen {
        return Screen(
            id = "pay_bills",
            title = "Pay Bills",
            content = Widget(
                type = "lazy_column",
                children = listOf(
                    Widget(
                        type = "text",
                        properties = buildJsonObject {
                            put("text", "Pending Bills")
                            put("style", "titleMedium")
                            put("padding", 16)
                        }
                    ),
                    Widget(
                        type = "bill_card",
                        properties = buildJsonObject {
                            put("provider", "ICICI Bank Credit Card")
                            put("amount", "₹52,400.00")
                            put("due_date", "Due in 2 days")
                            put("card_number", "**** 5678")
                            put("type", "credit_card")
                        }
                    ),
                    Widget(
                        type = "bill_card",
                        properties = buildJsonObject {
                            put("provider", "Jio Postpaid")
                            put("amount", "₹799.00")
                            put("due_date", "Due in 5 days")
                            put("phone_number", "+91 91234 56789")
                            put("type", "phone_bill")
                        }
                    ),
                    Widget(
                        type = "bill_card",
                        properties = buildJsonObject {
                            put("provider", "TATA Power")
                            put("amount", "₹4,250.00")
                            put("due_date", "Overdue")
                            put("type", "utility")
                        }
                    )
                )
            )
        )
    }

    fun getSendMoneyScreen(): Screen {
        return Screen(
            id = "send_money",
            title = "Send Money",
            content = Widget(
                type = "lazy_column",
                children = listOf(
                    Widget(
                        type = "text",
                        properties = buildJsonObject {
                            put("text", "Recent Recipients")
                            put("style", "titleMedium")
                            put("padding", 16)
                        }
                    ),
                    createPayee("Arjun Sharma", "SBI - 1234", "Indian"),
                    createPayee("Michael Scott", "Chase - 9988", "USA"),
                    createPayee("Priya Patel", "HDFC - 5566", "Indian"),
                    createPayee("Sarah Jenkins", "Bank of America - 1122", "USA")
                )
            )
        )
    }

    private fun createPayee(name: String, details: String, country: String) = Widget(
        type = "payee_item",
        properties = buildJsonObject {
            put("name", name)
            put("details", details)
            put("country", country)
        }
    )

    fun getScanQrScreen(): Screen {
        return Screen(
            id = "scan_qr",
            title = "Scan QR Code",
            content = Widget(
                type = "column",
                children = listOf(
                    Widget(
                        type = "text",
                        properties = buildJsonObject {
                            put("text", "Align QR code within the frame")
                            put("style", "titleMedium")
                            put("padding", 16)
                        }
                    )
                )
            )
        )
    }

    private fun createProgressItem(label: String, progress: Float, details: String) = Widget(
        type = "progress_item",
        properties = buildJsonObject {
            put("label", label)
            put("progress", progress)
            put("details", details)
        }
    )

    private fun createTransaction(title: String, subtitle: String, amount: String, date: String) = Widget(
        type = "transaction_item",
        properties = buildJsonObject {
            put("title", title)
            put("subtitle", subtitle)
            put("amount", amount)
            put("date", date)
        }
    )
}
