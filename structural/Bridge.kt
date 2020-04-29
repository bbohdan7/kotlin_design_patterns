/**
 * Bridge pattern is needed when we need to decouple abstraction from implementation that those ones vary independently.
 * When using inheritance implementation strongly related to the abstraction which obstruct next independent alteration.
 * 1) Necessary to place abstraction and implementation to the different classes hierarchies.
 * 2) Note! Developer which decided to add Bridge to his project has to take a great care about new dependencies
 * as it can crush entire Bridge.
 * */

interface DrawAPI {
    fun drawCircle(radius: Int, x: Int, y: Int): Unit
}

class RedCircle : DrawAPI {
    override fun drawCircle(radius: Int, x: Int, y: Int) = println("Red circle with radius [$x, $y]")
}

class GreenCircle : DrawAPI {
    override fun drawCircle(radius: Int, x: Int, y: Int) = println("Green circle with radius [$x, $y]")
}

class BlueCircle : DrawAPI {
    override fun drawCircle(radius: Int, x: Int, y: Int) = println("Blue circle with radius [$x, $y]")
}

abstract class Shape(protected val api: DrawAPI) {
    abstract fun draw(): Unit
}

class Circle(private val radius: Int, private val x: Int, private val y: Int, api: DrawAPI) : Shape(api) {
    override fun draw() = api.drawCircle(radius, x, y)
}

class Test {
    @Test
    fun test() {
        val red = Circle(radius = 100, x = 5, y = 10, api = RedCircle())
        val green = Circle(radius = 200, x = 10, y = 20, api = GreenCircle())
        val blue = Circle(radius = 300, x = 20, y = 30, api = BlueCircle())

        red.draw()
        green.draw()
        blue.draw()
    }
}