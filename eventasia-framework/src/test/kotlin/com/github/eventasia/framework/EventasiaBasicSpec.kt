package com.github.eventasia.framework

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class EventasiaBasicSpec : Spek({
    describe("Basic usage") {
        it("Can be simple") {
            println("BASIC and Simple")
        }
    }
    describe("Extended usage") {
        it("Can be complex") {
            println("EXTENDED and complex")
        }
    }
})