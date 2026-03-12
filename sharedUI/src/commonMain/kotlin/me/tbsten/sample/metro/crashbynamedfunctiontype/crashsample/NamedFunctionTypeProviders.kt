package me.tbsten.sample.metro.crashbynamedfunctiontype.crashsample

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Named
import dev.zacsweers.metro.Provides

@ContributesTo(AppScope::class)
interface NamedFunctionTypeProviders {
    // 🙆‍♂️ This won't crash.
    @Provides
    fun provideDoSomething(): () -> Unit = { }

    // 🚨 This will crash !!!
    @Provides
    fun provideDefaultDoSomething(): @Named("default") () -> Unit = { }
}
