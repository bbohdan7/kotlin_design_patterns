import org.junit.Test
/**
 * Data driven pattern which task is to wrap some request as command object and pass it to the invoker.
 * Invoker object searches the necessary object which can handle this command and passes the command to the
 * corresponding object which executes the command.
 * Another explanation may sound like if it's necessary to send request to the object without knowing who is
 * the recipient and what kind of operation must be done on it.
 * 1) Note! the Command pattern may breach OOP principals.
 * 2) Use it only when you need to UNDO something performed earlier.
 * */

interface Order {
    fun execute(): Unit
}

class Stock constructor(private val name: String = "abc", private val qty: Int = 10) {

    fun buy() = println("Buying $name quantity $qty")

    fun sell() = println("Selling $name quantity $qty")
}

class BuyStock constructor(private val stock: Stock) : Order {
    override fun execute() = stock.buy()
}

class SellStock constructor(private val stock: Stock) : Order {
    override fun execute() = stock.sell()
}

class Broker {
    private var orders: List<Order> = emptyList()

    fun makeOrder(order: Order): Unit {
        orders = orders + order
    }

    fun placeOrders(): Unit {
        orders.forEach(Order::execute)
        orders = emptyList()
    }
}

class Test {
    @Test
    fun test(): Unit {
        val stock = Stock("Silpo", qty = 15)
        val buy = BuyStock(stock)
        val sell = SellStock(stock)

        val broker = Broker()
        broker.makeOrder(buy)
        broker.makeOrder(sell)

        broker.placeOrders()
    }
}
