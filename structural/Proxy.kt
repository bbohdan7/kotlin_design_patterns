/**
 * Proxy pattern describes a class with delegated functionality to another one.
 * It can be applied when it's necessary to manage access to an object in order to create large object by demand.
 * A client works with system interface that contains a proxy with delegated functionality.
 * */

interface Image {
    fun display(): Unit
}

class Jpeg(private val filename: String) : Image {
    init {
        loadImage()
    }

    override fun display() = println("Displaying $filename")

    private fun loadImage(): Unit = println("Loading $filename")
}

class ProxyImage(private val filename: String) : Image {
    override fun display(): Unit = Jpeg(filename).display()
}

class Test {
    @Test
    fun test(): Unit {
        val image = ProxyImage("IMG00001.jpg")
        image.display()
    }
}