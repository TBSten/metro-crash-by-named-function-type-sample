# Crash metro by Named function type

- Core code: [sharedUI/src/commonMain/kotlin/me/tbsten/sample/metro/crashbynamedfunctiontype/crashsample/](sharedUI/src/commonMain/kotlin/me/tbsten/sample/metro/crashbynamedfunctiontype/crashsample)
- Core commit: [42ff1e0](https://github.com/TBSten/metro-crash-by-named-function-type-sample/commit/42ff1e0f1171bb329e7524fe1e0920e12d3df27c)

## Summary

Function types can be provided (as expected)

```kt
@ContributesTo(AppScope::class)
interface NamedFunctionTypeProviders {
    // 🙆‍♂️ This won't crash.
    @Provides
    fun provideDoSomething(): () -> Unit = { }
}
```

However, adding the Named annotation causes an error (unexpected).

```kt
@ContributesTo(AppScope::class)
interface NamedFunctionTypeProviders {
    // 🚨 This will crash !!!
    @Provides
    fun provideDefaultDoSomething(): @Named("default") () -> Unit = { }
}
```

Stacktrace:

```
e: org.jetbrains.kotlin.util.FileAnalysisException: Somewhere in file /Users/tbsten/Downloads/Crash-by-Named-function-type/sharedUI/src/commonMain/kotlin/me/tbsten/sample/metro/crashbynamedfunctiontype/crashsample/NamedFunctionTypeProviders.kt: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments: Expected FirResolvedTypeRef with ConeKotlinType but was FirUserTypeRefImpl
	at org.jetbrains.kotlin.util.AnalysisExceptionsKt.wrapIntoFileAnalysisExceptionIfNeeded(AnalysisExceptions.kt:62)
	at org.jetbrains.kotlin.fir.FirCliExceptionHandler.handleExceptionOnFileAnalysis(Utils.kt:269)
	at org.jetbrains.kotlin.fir.resolve.transformers.FirSupertypeResolverTransformer.transformFile(FirSupertypesResolution.kt:881)
	at org.jetbrains.kotlin.fir.declarations.FirFile.transform(FirFile.kt:47)
	at org.jetbrains.kotlin.fir.resolve.transformers.FirTransformerBasedResolveProcessor.processFile(FirResolveProcessor.kt:53)
	at org.jetbrains.kotlin.fir.resolve.transformers.FirTotalResolveProcessor.process(FirTotalResolveProcessor.kt:36)
	at org.jetbrains.kotlin.fir.pipeline.AnalyseKt.runResolution(analyse.kt:24)
	at org.jetbrains.kotlin.fir.pipeline.FirUtilsKt.resolveAndCheckFir(firUtils.kt:76)
	at org.jetbrains.kotlin.cli.pipeline.jvm.JvmFrontendPipelinePhase.executePhase(JvmFrontendPipelinePhase.kt:269)
	at org.jetbrains.kotlin.cli.pipeline.jvm.JvmFrontendPipelinePhase.executePhase(JvmFrontendPipelinePhase.kt:55)
	at org.jetbrains.kotlin.cli.pipeline.PipelinePhase.phaseBody(PipelinePhase.kt:68)
	at org.jetbrains.kotlin.cli.pipeline.PipelinePhase.phaseBody(PipelinePhase.kt:58)
	at org.jetbrains.kotlin.config.phaser.NamedCompilerPhase.invoke(CompilerPhase.kt:102)
	at org.jetbrains.kotlin.backend.common.phaser.CompositePhase.invoke(PhaseBuilders.kt:22)
	at org.jetbrains.kotlin.config.phaser.CompilerPhaseKt.invokeToplevel(CompilerPhase.kt:53)
	at org.jetbrains.kotlin.cli.pipeline.AbstractCliPipeline.runPhasedPipeline(AbstractCliPipeline.kt:109)
	at org.jetbrains.kotlin.cli.pipeline.AbstractCliPipeline.execute(AbstractCliPipeline.kt:68)
	at org.jetbrains.kotlin.cli.jvm.K2JVMCompiler.doExecutePhased(K2JVMCompiler.kt:51)
	at org.jetbrains.kotlin.cli.jvm.K2JVMCompiler.doExecutePhased(K2JVMCompiler.kt:42)
	at org.jetbrains.kotlin.cli.common.CLICompiler.execImpl(CLICompiler.kt:89)
	at org.jetbrains.kotlin.cli.common.CLICompiler.exec(CLICompiler.kt:359)
	at org.jetbrains.kotlin.incremental.IncrementalJvmCompilerRunnerBase.runCompiler(IncrementalJvmCompilerRunnerBase.kt:178)
	at org.jetbrains.kotlin.incremental.IncrementalJvmCompilerRunnerBase.runCompiler(IncrementalJvmCompilerRunnerBase.kt:40)
	at org.jetbrains.kotlin.incremental.IncrementalCompilerRunner.doCompile(IncrementalCompilerRunner.kt:504)
	at org.jetbrains.kotlin.incremental.IncrementalCompilerRunner.compileImpl(IncrementalCompilerRunner.kt:418)
	at org.jetbrains.kotlin.incremental.IncrementalCompilerRunner.compileNonIncrementally(IncrementalCompilerRunner.kt:301)
	at org.jetbrains.kotlin.incremental.IncrementalCompilerRunner.compile(IncrementalCompilerRunner.kt:128)
	at org.jetbrains.kotlin.daemon.CompileServiceImplBase.execIncrementalCompiler(CompileServiceImpl.kt:684)
	at org.jetbrains.kotlin.daemon.CompileServiceImplBase.access$execIncrementalCompiler(CompileServiceImpl.kt:94)
	at org.jetbrains.kotlin.daemon.CompileServiceImpl.compile(CompileServiceImpl.kt:1810)
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(Unknown Source)
	at java.base/java.lang.reflect.Method.invoke(Unknown Source)
	at java.rmi/sun.rmi.server.UnicastServerRef.dispatch(Unknown Source)
	at java.rmi/sun.rmi.transport.Transport$1.run(Unknown Source)
	at java.rmi/sun.rmi.transport.Transport$1.run(Unknown Source)
	at java.base/java.security.AccessController.doPrivileged(Unknown Source)
	at java.rmi/sun.rmi.transport.Transport.serviceCall(Unknown Source)
	at java.rmi/sun.rmi.transport.tcp.TCPTransport.handleMessages(Unknown Source)
	at java.rmi/sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run0(Unknown Source)
	at java.rmi/sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.lambda$run$0(Unknown Source)
	at java.base/java.security.AccessController.doPrivileged(Unknown Source)
	at java.rmi/sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run(Unknown Source)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source)
	at java.base/java.lang.Thread.run(Unknown Source)
Caused by: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments: Expected FirResolvedTypeRef with ConeKotlinType but was FirUserTypeRefImpl
	at org.jetbrains.kotlin.fir.types.FirTypeUtilsKt.getConeType(FirTypeUtils.kt:201)
	at org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt.toAnnotationClassLikeType(FirAnnotationUtils.kt:32)
	at org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt.toAnnotationLookupTag(FirAnnotationUtils.kt:35)
	at org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt.toAnnotationClassId(FirAnnotationUtils.kt:41)
	at org.jetbrains.kotlin.fir.types.FirFunctionTypeKindServiceImpl.extractAllSpecialKindsForFunctionTypeRef(FirFunctionTypeKindServiceImpl.kt:76)
	at dev.zacsweers.metro.compiler.fir.generators.ProvidesFactorySupertypeGenerator.createFunctionType(ProvidesFactoryFirGenerator.kt:491)
	at dev.zacsweers.metro.compiler.fir.generators.ProvidesFactorySupertypeGenerator.computeAdditionalSupertypesForGeneratedNestedClass(ProvidesFactoryFirGenerator.kt:440)
	at dev.zacsweers.metro.compiler.fir.generators.KotlinOnlyFirSupertypeGenerationExtension.computeAdditionalSupertypesForGeneratedNestedClass(KotlinOnlyFirSupertypeGenerationExtension.kt:28)
	at org.jetbrains.kotlin.fir.resolve.transformers.FirSupertypeResolverVisitor.addSupertypesToGeneratedNestedClasses(FirSupertypesResolution.kt:487)
	at org.jetbrains.kotlin.fir.resolve.transformers.FirSupertypeResolverVisitor.resolveSpecificClassLikeSupertypes$lambda$0(FirSupertypesResolution.kt:438)
	at org.jetbrains.kotlin.fir.resolve.transformers.FirSupertypeResolverVisitor.resolveSpecificClassLikeSupertypes(FirSupertypesResolution.kt:358)
	at org.jetbrains.kotlin.fir.resolve.transformers.FirSupertypeResolverVisitor.resolveSpecificClassLikeSupertypes(FirSupertypesResolution.kt:404)
	at org.jetbrains.kotlin.fir.resolve.transformers.FirSupertypeResolverVisitor.visitRegularClass(FirSupertypesResolution.kt:380)
	at org.jetbrains.kotlin.fir.resolve.transformers.FirSupertypeResolverVisitor.visitRegularClass(FirSupertypesResolution.kt:186)
	at org.jetbrains.kotlin.fir.declarations.FirRegularClass.accept(FirRegularClass.kt:50)
	at org.jetbrains.kotlin.fir.declarations.impl.FirFileImpl.acceptChildren(FirFileImpl.kt:60)
	at org.jetbrains.kotlin.fir.resolve.transformers.FirSupertypeResolverVisitor.visitDeclarationContent(FirSupertypesResolution.kt:368)
	at org.jetbrains.kotlin.fir.resolve.transformers.FirSupertypeResolverVisitor.visitFile(FirSupertypesResolution.kt:554)
	at org.jetbrains.kotlin.fir.resolve.transformers.FirSupertypeResolverVisitor.visitFile(FirSupertypesResolution.kt:186)
	at org.jetbrains.kotlin.fir.declarations.FirFile.accept(FirFile.kt:43)
	at org.jetbrains.kotlin.fir.resolve.transformers.FirSupertypeResolverTransformer.transformFile(FirSupertypesResolution.kt:65)
	... 42 more
```