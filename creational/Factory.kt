import org.junit.Test
/**
 * Factory allow to construct an object without exposing the creation logic to the client.
 * */

interface Shape {
    fun draw(): Unit
}

class Rectangle : Shape {
    override fun draw(): Unit = println("Rectangle")
}

class Circle : Shape {
    override fun draw(): Unit = println("Circle")
}

class Square : Shape {
    override fun draw(): Unit = println("Square")
}

enum class ShapeName {
    RECTANGLE, CIRCLE, SQUARE
}

object ShapeFactory {
    fun getShape(shapeName: ShapeName): Shape = when (shapeName) {
        ShapeName.RECTANGLE -> Rectangle()
        ShapeName.CIRCLE -> Circle()
        ShapeName.SQUARE -> Square()
        else -> throw UnknownShapeException(message = "There is no such shape!")
    }
}

class UnknownShapeException : Exception {
    constructor() : super()
    constructor(message: String) : super(message)
}

class Test{
    @Test
    fun test(): Unit {
        val shape: Shape = ShapeFactory.getShape(ShapeName.CIRCLE)
        shape.draw()
    }
}