package utils

sealed class ZzzResult<out R> {
    data class Success<out T>(val data: T) : ZzzResult<T>()
    data class Error(val exception: Exception) : ZzzResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}