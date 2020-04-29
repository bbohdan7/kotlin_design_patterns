/**
 * Decorator pattern allows to add a new functionality to an existing object without altering its structure.
 * It acts as a wrapper related to existing object.
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

abstract class ShapeDecorator(private val shape: Shape) : Shape {
    override fun draw(): Unit = shape.draw()
}

class ColoredShapeDecorator(private val shape: Shape) : ShapeDecorator(shape) {
    override fun draw(): Unit {
        shape.draw()
        setBorderColor("red")
    }

    private fun setBorderColor(color: String): Unit = println("Border color: $color")
}

class Test {
    @Test
    fun test() {
        //Circle object
        val circle: Shape = Circle()
        val coloredCircle = ColoredShapeDecorator(circle)
        
        println("Existing circle:")
        circle.draw() //Existing functionality

        println("Decorated circle:")
        coloredCircle.draw() //New functionality added with Decorator

        //Rectangle object
        val rectangle: Shape = Rectangle()
        val coloredRectangle = ColoredShapeDecorator(rectangle)

        println("Existing rectangle:")
        rectangle.draw() //Existing functionality

        println("Decorated rectangle:")
        coloredRectangle.draw() //New functionality added with Decorator

        //Square object
        val square: Shape = Square()
        val coloredSquare = ColoredShapeDecorator(square)

        println("Existing square:")
        square.draw() //Existing functionality

        println("Decorated square:")
        coloredSquare.draw() //New functionality added with Decorator
    }
}