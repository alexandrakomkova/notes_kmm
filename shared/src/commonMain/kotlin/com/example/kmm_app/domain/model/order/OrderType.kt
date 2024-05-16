package com.example.kmm_app.domain.model.order

sealed class OrderType {
    data object Ascending: OrderType()
    data object Descending: OrderType()
}