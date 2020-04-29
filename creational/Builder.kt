/**
 * Builder pattern is used to create complex object by using simple objects.
 * */
interface Item {
    fun name(): String
    fun packing(): Packing
    fun price(): Float
}

interface Packing {
    fun pack(): String
}

class Wrapper : Packing {
    override fun pack(): String = "wrapper"
}

class Bottle : Packing {
    override fun pack(): String = "bottle"
}

abstract class Burger : Item {
    override fun packing(): Packing = Wrapper()

    abstract override fun price(): Float
}

abstract class Drink : Item {
    override fun packing(): Packing = Bottle()

    abstract override fun price(): Float
}

class VeganBurger : Burger() {
    override fun price(): Float = 25f

    override fun name(): String = "Vegan Burger"
}

class ChickenBurger : Burger() {
    override fun price(): Float = 30f

    override fun name(): String = "Chicken Burger"
}

class CocaCola : Drink() {
    override fun price(): Float = 30f

    override fun name(): String = "Coca-Cola"
}

class Pepsi : Drink() {
    override fun price(): Float = 30f

    override fun name(): String = "Pepsi"
}

class Order {
    private var items: List<Item> = emptyList()

    fun addItem(item: Item): Unit {
        items = items + item
    }

    fun getCost(): Float = items.stream().mapToDouble { it.price().toDouble() }.sum().toFloat()

    fun showItems(): Unit = items.forEach {
        println("Item: ${it.name()}, packing: ${it.packing().pack()}, price: ${it.price()}$")
    }
}

object OrderBuilder {
    fun prepareVeganOrder(): Order = Order().apply {
        addItem(VeganBurger())
        addItem(CocaCola())
    }

    fun prepareChickenOrder(): Order = Order().apply {
        addItem(ChickenBurger())
        addItem(Pepsi())
    }
}

class Test {
    @Test
    fun test(): Unit {
        val vegan: Order = OrderBuilder.prepareVeganOrder()

        println("Vegan menu: ")
        vegan.showItems()

        val chicken = OrderBuilder.prepareChickenOrder()
        println("Chicken menu: ")
        chicken.showItems()
    }
}