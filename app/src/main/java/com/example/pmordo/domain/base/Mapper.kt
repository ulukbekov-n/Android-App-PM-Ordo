package com.example.pmordo.domain.base

interface Mapper<From, To> {
    fun map(from: From): To
}