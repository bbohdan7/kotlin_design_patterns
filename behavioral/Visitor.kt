/**
 * Visitor pattern represents a class that changes executing algorithm of an element class. This pattern provides a way
 * to change behaviour depending on how visitor varies.
 * */
interface ComputerPart {
    fun accept(visitor: Visitor): Unit
}

interface Visitor {
    fun visit(computer: Computer): Unit
    fun visit(mouse: Mouse): Unit
    fun visit(keyboard: Keyboard): Unit
    fun visit(monitor: Monitor): Unit
}

class Computer : ComputerPart {

    private val parts: Array<ComputerPart> = arrayOf(
        Mouse(), Keyboard(), Monitor()
    )

    override fun accept(visitor: Visitor) {
        parts.forEach { it.accept(visitor) }
        visitor.visit(this)
    }
}

class Mouse : ComputerPart {
    override fun accept(visitor: Visitor): Unit = visitor.visit(this)
}

class Keyboard : ComputerPart {
    override fun accept(visitor: Visitor): Unit = visitor.visit(this)
}

class Monitor : ComputerPart {
    override fun accept(visitor: Visitor): Unit = visitor.visit(this)
}

class ComputerVisitor : Visitor {
    override fun visit(computer: Computer) = println("Displaying Computer")

    override fun visit(mouse: Mouse) = println("Displaying Mouse")

    override fun visit(keyboard: Keyboard) = println("Displaying Keyboard")

    override fun visit(monitor: Monitor) = println("Displaying Monitor")
}

class Test {
    @Test
    fun test() {
        val computer: ComputerPart = Computer()
        computer.accept(ComputerVisitor())
    }
}