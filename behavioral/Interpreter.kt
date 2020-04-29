/**
 * Interpreter pattern allows us to evaluate language grammar or expression. It provides an interface which task is
 * to interpret a given context (e.g. SQL Parsing, symbol processing etc.)
 * */
interface Expression {
    fun interpret(context: String): Boolean
}

class TerminalExpression(private val data: String) : Expression {
    override fun interpret(context: String): Boolean = context.contains(data)
}

class OrExpresion(private val exp1: Expression, private val exp2: Expression) : Expression {
    override fun interpret(context: String): Boolean = exp1.interpret(context) || exp2.interpret(context)
}

class AndExpression(private val exp1: Expression, private val exp2: Expression) : Expression {
    override fun interpret(context: String): Boolean = exp1.interpret(context) && exp2.interpret(context)
}

class Test {
    @Test
    fun test() {
        println("John is male? ${maleExpression().interpret("John")}")
        println("Julie is married woman? ${marriedWomanExpression().interpret("Married Julia")}")
    }

    private fun maleExpression(): Expression =
        OrExpresion(TerminalExpression("Robert"), TerminalExpression("John"))

    private fun marriedWomanExpression(): Expression =
        AndExpression(TerminalExpression("Julia"), TerminalExpression("Married"))
}