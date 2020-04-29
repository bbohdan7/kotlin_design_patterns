/**
 * Facade pattern hides complexities of the system and provides interface between two subsystems.
 * This pattern is very similar to Adapter and more over I can say it's the same as Adapter but difference between them
 * is scaling: Adapter is a low-level pattern which helps to stick together two incompatible classes and Facade works
 * on high-level by combining together two incompatible subsystems.
 * Facade ensures that GRASP principals such as Low Coupling and High Coherence will be accomplished
 * */
interface Shape {
    fun draw(): Unit
}

class Circle : Shape {
    override fun draw() = println("Circle")
}

class Rectangle : Shape {
    override fun draw() = println("Rectangle")
}

class Square : Shape {
    override fun draw() = println("Square")
}

class ShapeFacade {
    fun drawCircle(): Unit = Circle().draw()
    fun drawRectangle(): Unit = Rectangle().draw()
    fun drawSquare(): Unit = Square().draw()
}

class Test{
    @Test
    fun test(): Unit{
        val facade = ShapeFacade()

        facade.drawCircle()
        facade.drawRectangle()
        facade.drawSquare()
    }
}