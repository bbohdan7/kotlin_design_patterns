/**
 * Chain of Responsibility pattern decouples sender and receiver of a request based on type of request.
 * */

enum class LoggerLevel {
    ERROR, DEBUG, INFO
}

abstract class AbstractLogger {
    lateinit var nextLogger: AbstractLogger
    protected abstract fun write(message: String): Unit
    protected open var level = 0

    fun logMessage(level: Int, message: String): Unit =
        if (this.level <= level) write(message) else nextLogger.logMessage(level, message)
}

class ConsoleLogger(override var level: Int) : AbstractLogger() {
    override fun write(message: String) {
        println("Console logger >> $message")
    }
}

class FileLogger(override var level: Int) : AbstractLogger() {
    override fun write(message: String) {
        println("File logger >> $message")
    }
}

class ErrorLogger(override var level: Int) : AbstractLogger() {
    override fun write(message: String) {
        println("Error logger >> $message")
    }
}

class Test {
    @Test
    fun test() {
        val chain = getLoggerChain()

        chain.logMessage(LoggerLevel.ERROR.ordinal, "This is an error")
        chain.logMessage(LoggerLevel.INFO.ordinal, "This is an info")
        chain.logMessage(LoggerLevel.DEBUG.ordinal, "Debugging...")
    }

    private fun getLoggerChain(): AbstractLogger {
        val error = ErrorLogger(LoggerLevel.ERROR.ordinal)
        val console = ConsoleLogger(LoggerLevel.INFO.ordinal)
        val file = FileLogger(LoggerLevel.ERROR.ordinal)

        error.nextLogger = console
        console.nextLogger = file

        return error
    }
}