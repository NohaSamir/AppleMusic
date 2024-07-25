#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME} #end

interface ${Mapper_Type}Mapper<S : ${Source_Interface}, T : ${Target_Interface}> {
    fun S.mapFrom${Mapper_Type}Model(): T {
        throw UnsupportedOperationException()
    }

    fun T.mapTo${Mapper_Type}Model(): S {
        throw UnsupportedOperationException()
    }
}