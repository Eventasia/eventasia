package com.github.eventasia.framework

import java.io.Serializable
import java.util.*

interface Aggregate<T : Serializable> : Serializable {

    val aggregateId: UUID

    val version: Long

    fun incrementVersion(): Long

}