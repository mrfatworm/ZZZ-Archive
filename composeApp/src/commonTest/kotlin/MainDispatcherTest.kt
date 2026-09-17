/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

import kotlin.test.AfterTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

/**
 * Multiplatform replacement for the JUnit 4 `MainDispatcherRule`: ViewModels run their work on
 * `viewModelScope`, which needs a `Dispatchers.Main` that exists on desktop and iOS too.
 *
 * The dispatcher is installed from `init` rather than from `@BeforeTest` on purpose. Subclasses
 * build their ViewModel in a property initializer or in their own `@BeforeTest`, and only
 * construction order is guaranteed to run the superclass first — the relative order of two
 * `@BeforeTest` functions is not.
 */
@OptIn(ExperimentalCoroutinesApi::class)
abstract class MainDispatcherTest(protected val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()) {
    init {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun resetMainDispatcher() {
        Dispatchers.resetMain()
    }

    /**
     * Use this instead of `runTest` in a ViewModel test.
     *
     * A bare `runTest` adopts the scheduler of the `TestDispatcher` installed as `Dispatchers.Main`,
     * and would then skip the ViewModel's `delay`s while draining it. The "refresh every ten
     * minutes" flows (`getAllForumListPeriodically`, `getNewsListPeriodically`, …) are
     * `while (true) { emit(); delay() }` loops, so skipping their delay spins forever. Giving
     * `runTest` a scheduler of its own leaves the ViewModel's timers parked instead.
     */
    fun runViewModelTest(testBody: suspend TestScope.() -> Unit): TestResult =
        runTest(StandardTestDispatcher(TestCoroutineScheduler()), testBody = testBody)
}
