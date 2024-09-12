#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME} #end

import com.vama.domain.model.Result
import javax.inject.Inject

class ${Use_Case_Name}UseCase @Inject constructor() {

    suspend operator fun invoke(): Result<${Return_Type}> {
        return Result.Error()
    }
}