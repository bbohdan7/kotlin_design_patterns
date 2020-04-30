/**
 * Observer (Listener, Public-Subscribe) pattern is used when necessary to notify one object about altering state of another object and
 * keep low coupling manner between subscribers. Observer can be used for one-to-many relationship between objects if
 * one object is to be modified another related objects will be notified about modifications.
 * */

abstract class Observer {
    abstract fun update(): Unit
}

class Subject {
    private var observers: List<Observer> = emptyList()

    private var state: Int = 0

    fun getState() = state

    fun setState(state: Int) {
        this.state = state
        notifyAllObservers()
    }

    private fun notifyAllObservers(): Unit = observers.forEach(Observer::update)

    fun attach(observer: Observer): Unit {
        observers = observers + observer
    }
}

class BinaryObserver(private val subject: Subject) : Observer() {
    init {
        subject.attach(this)
    }

    override fun update(): Unit = println("Binary String ${Integer.toBinaryString(subject.getState())}")
}

class OctalObserver(private val subject: Subject) : Observer() {
    init {
        subject.attach(this)
    }

    override fun update(): Unit = println("Octal String ${Integer.toOctalString(subject.getState())}")
}

class HexObserver(private val subject: Subject) : Observer() {
    init {
        subject.attach(this)
    }

    override fun update(): Unit = println("Hex String ${Integer.toHexString(subject.getState())}")
}

class Test {
    @Test
    fun test() {
        val subject = Subject()

        HexObserver(subject)
        OctalObserver(subject)
        BinaryObserver(subject)

        var state = 15

        println("Initial state $state")
        subject.setState(state)

        state = 20
        println("Modified state $state")
        subject.setState(state)
    }
}