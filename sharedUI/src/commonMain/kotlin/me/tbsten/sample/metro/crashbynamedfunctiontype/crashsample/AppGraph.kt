package me.tbsten.sample.metro.crashbynamedfunctiontype.crashsample

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Named
import dev.zacsweers.metro.SingleIn

@SingleIn(AppScope::class)
@DependencyGraph(AppScope::class)
internal interface AppGraph {
//    val doSomething: () -> Unit
    val authDoSomething: @Named("auth") () -> Unit
    val noauthDoSomething: @Named("noauth") () -> Unit
}

