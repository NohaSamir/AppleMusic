package com.vama.applemusic.ui

import com.vama.applemusic.ui.model.UiIntent
import com.vama.applemusic.ui.model.UiState
import com.vama.applemusic.ui.util.ViewModelStateHandler
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals

/**
 * This is a skeleton for testing a ViewModel.
 * It will throw an UncompletedCoroutinesError if the number of expected states is not equal to the number of actual states.
 * so, if you face this error, you should check the number of expected states or add a delay to the test.
 * @param givenInitialState the initial state of the ViewModel.
 * @param givenIntent the intent to be set to the ViewModel.
 * @param expectedStates the expected states to be emitted from the ViewModel.
 * @param initializeViewModel a lambda to initialize the ViewModel.
 * @param mockDependency a lambda to mock the dependencies of the ViewModel.
 * @param verificationCalls a lambda to verify the calls of the dependencies of the ViewModel.
 */
fun <S : UiState, I : UiIntent> viewModelTestSkeleton(
    givenInitialState: S?,
    givenIntent: I?,
    expectedStates: List<S>,
    mockDependency: suspend () -> Unit = {},
    initializeViewModel: () -> ViewModelStateHandler<S, I>,
    verificationCalls: suspend () -> Unit = {}
) = runTest {
    mockDependency()
    val viewModel = initializeViewModel()

    givenInitialState?.let {
        viewModel.setState { it }
    }

    givenIntent?.let {
        viewModel.setIntent(it)
    }

    val numberOfStates =
        expectedStates.size.takeIf { givenInitialState == null } ?: (expectedStates.size + 1)
    val uiStatus = viewModel.uiState.take(numberOfStates).toList().toMutableList()

    if (givenInitialState != null && uiStatus.size > 0) {
        uiStatus.removeAt(0) // To remove the initial state
    }
    expectedStates.forEachIndexed { index, expectedStatus ->
        assertEquals(expectedStatus, uiStatus.getOrNull(index))
    }
    verificationCalls()
}