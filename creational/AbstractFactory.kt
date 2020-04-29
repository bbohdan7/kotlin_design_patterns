/**
 * Abstract Factory is something like Factory of Factories or super factory.
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

class Cube : Shape {
    override fun draw(): Unit = println("Cube")
}

class Sphere : Shape {
    override fun draw(): Unit = println("Sphere")
}

enum class ShapeName {
    RECTANGLE, CIRCLE, SQUARE, CUBE, SPHERE
}

enum class ShapeType {
    FLAT, SPATIAL
}

class UnknownShapeException : Exception {
    constructor() : super()
    constructor(message: String) : super(message)
}

class UnknownTypeOfShapeException : Exception {
    constructor() : super()
    constructor(message: String = "There is no such shape type.") : super(message)
}

abstract class AbstractFactory {
    abstract fun getShape(shapeName: ShapeName): Shape
}

class FlatShapeFactory : AbstractFactory() {
    override fun getShape(shapeName: ShapeName): Shape = when (shapeName) {
        ShapeName.CIRCLE -> Circle()
        ShapeName.SQUARE -> Square()
        ShapeName.RECTANGLE -> Rectangle()
        else -> throw UnknownShapeException("This factory may produce only 2D shapes.")
    }
}

class SpatialShapeFactory : AbstractFactory() {
    override fun getShape(shapeName: ShapeName): Shape = when (shapeName) {
        ShapeName.CUBE -> Cube()
        ShapeName.SPHERE -> Sphere()
        else -> throw UnknownShapeException("This factory may produce only 3D shapes.")
    }
}

object FactoryProducer {
    fun getFactory(shapeType: ShapeType): AbstractFactory = when (shapeType) {
        ShapeType.FLAT -> FlatShapeFactory()
        ShapeType.SPATIAL -> SpatialShapeFactory()
        else -> throw UnknownShapeException()
    }
}

class Test {
    @Test
    fun test(): Unit {
        val factory: Shape = FactoryProducer.getFactory(ShapeType.FLAT).getShape(ShapeName.RECTANGLE)
        factory.draw()
    }
}