package com.github.eventasia.framework

import java.io.Serializable
import java.util.*

interface Command : Serializable {
    fun getAggregateId(): UUID
}