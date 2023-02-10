package io.bhimsur.app.web.model.response

import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection
import java.util.*

@JsonRootName("errors")
@RegisterForReflection
data class ErrorResponse(var body: MutableList<String>) {

    constructor() : this(LinkedList()) {}

    constructor(error: String?) : this() {
        if (error != null) {
            body.add(error)
        } else {
            body = LinkedList()
        }
    }
}
