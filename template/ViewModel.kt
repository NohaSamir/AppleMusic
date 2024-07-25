#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME} #end

import com.vama.applemusic.ui.util.ViewModelStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ${ViewModel_Name}ViewModel @Inject constructor() : ViewModelStateHandler<${ViewModel_Name}UiState, ${ViewModel_Name}UiIntent>() {

    override fun createInitialState(): ${ViewModel_Name}UiState {
        return TODO()
    }

    override fun handleIntent(intent: ${ViewModel_Name}UiIntent) {
        when (intent) {
            TODO()
        }
    }
}