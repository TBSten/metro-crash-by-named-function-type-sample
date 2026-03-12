package me.tbsten.sample.metro.crashbynamedfunctiontype.crashsample

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Named
import dev.zacsweers.metro.Provides

@ContributesTo(AppScope::class)
interface NamedFunctionTypeProviders {
    // 🙆‍♂️ This won't crash.
//    @Provides
//    fun provideDoSomething(): () -> Unit =
//        { println("provideDoSomething") }

    // 🚨 This will crash !!!
    // [Metro/DuplicateBinding] Multiple bindings found for kotlin.Function0<kotlin.Unit>
    //
    // @Named does not appear to be functioning
    @Provides
    fun provideDefaultDoSomething(): @Named("default") () -> Unit =
        { println("provideDefaultDoSomething()") }

    @Provides
    fun provideFakeDoSomething(): @Named("fake") () -> Unit =
        { println("provideFakeDoSomething()") }
}
