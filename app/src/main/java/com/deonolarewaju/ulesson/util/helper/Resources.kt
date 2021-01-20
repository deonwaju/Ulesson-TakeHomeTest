package com.deonolarewaju.ulesson.util.helper

data class Resources<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): Resources<T> {
            return Resources(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): Resources<T> {
            return Resources(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): Resources<T> {
            return Resources(Status.LOADING, data, null)
        }
    }
}