/**
 * Strategy Pattern is indispensable pattern in cases when necessary to change algorithm at run time.
 * */

interface Strategy {
    fun operate(num1: Int, num2: Int): Int
}

class Add : Strategy {
    override fun operate(num1: Int, num2: Int): Int = num1 + num2
}

class Subtract : Strategy {
    override fun operate(num1: Int, num2: Int): Int = num1 - num2
}

class Multiply : Strategy {
    override fun operate(num1: Int, num2: Int): Int = num1 * num2
}

class Divide : Strategy {
    override fun operate(num1: Int, num2: Int): Int = num1 / num2
}

class Context(private val strategy: Strategy) {
    fun execute(num1: Int, num2: Int): Int = strategy.operate(num1, num2)
}

class Test {
    @Test
    fun test() {
        var context = Context(Add())
        println("2 + 3 = ${context.execute(2, 3)}")

        context = Context(Subtract())
        println("10 - 5 = ${context.execute(10, 5)}")

        context = Context(Multiply())
        println("6 x 8 = ${context.execute(6, 8)}")

        context = Context(Divide())
        println("24 / 6 = ${context.execute(24, 6)}")
    }
}