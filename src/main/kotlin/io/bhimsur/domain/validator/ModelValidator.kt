package io.bhimsur.domain.validator

import io.bhimsur.domain.exception.ValidationException
import java.util.stream.Collectors
import javax.validation.ConstraintViolation
import javax.validation.Validator

class ModelValidator(private val validator: Validator) {
    fun <T> validate(model: T): T {
        val constraintViolations: Set<ConstraintViolation<T>> = validator.validate(model)
        if (constraintViolations.isNotEmpty()) {
            val messages: List<String> = constraintViolations.stream().map { it.message }.collect(Collectors.toList())
            throw ValidationException(messages)
        }
        return model
    }
}