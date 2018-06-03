package com.workoutperf.model

class CurrentSid: InheritableThreadLocal<Sid>() {
    override fun initialValue() = Sid.Anonymous
}

open class Sid(
        open val sid: String
) {
    companion object {
        val Owner = Sid("Owner")
        val Friends = Sid("Friends")
        val Everyone = Sid("Everyone")
        val Root = Sid("Root")
        val Anonymous = Sid("Anonymous")
        val current = CurrentSid()

    }

}