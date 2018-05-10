package com.example.rafaelanastacioalves.moby.application

class FakeCrashLibrary//TODO Think production ready
private constructor() {
    init {
        throw AssertionError("No instances.")
    }

    companion object {

        fun log(priority: Int, tag: String, message: String) {
            // TODO add log entry to circular buffer.
        }

        fun logWarning(t: Throwable) {
            // TODO report non-fatal warning.
        }

        fun logError(t: Throwable) {
            // TODO report non-fatal error.
        }
    }
}
