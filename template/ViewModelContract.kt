#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME} #end

import androidx.compose.runtime.Stable
import com.vama.applemusic.ui.model.UiIntent
import com.vama.applemusic.ui.model.UiState

@Stable
sealed class ${ViewModel_Name}UiState : UiState {
    data object Loading : ${ViewModel_Name}UiState()
    data object Retrying : ${ViewModel_Name}UiState()
    data class Success() : ${ViewModel_Name}UiState()
    data class Error(val message: String) : ${ViewModel_Name}UiState()
}

@Stable
sealed class ${ViewModel_Name}UiIntent : UiIntent {
    data object OnRetryClicked : ${ViewModel_Name}UiIntent()
}