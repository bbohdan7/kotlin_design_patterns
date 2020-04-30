/**
 * Memento pattern usually applied when you need to restore object's previous state.
 * */

data class Memento(var state: String)

class Originator {
    lateinit var state: String get set

    fun saveStateToMemento() = Memento(state)

    fun restoreStateFromMemento(memento: Memento) {
        this.state = memento.state
    }
}

class MementoHandler {
    private var mementos: List<Memento> = emptyList()

    fun add(state: Memento): Unit {
        mementos = mementos + state
    }

    fun get(index: Int): Memento = mementos[index]
}

class Test {
    @Test
    fun test() {
        val originator = Originator()
        val handler = MementoHandler()

        originator.state = "State #1"
        handler.add(originator.saveStateToMemento()) //Checkpoint #0
        originator.state = "State #2"
        handler.add(originator.saveStateToMemento()) //Checkpoint #1

        originator.state = "State #3"
        handler.add(originator.saveStateToMemento()) //Checkpoint #2

        originator.state = "State #4"
        println("Current state: ${originator.state}")

        //Rolling back to the first state
        originator.restoreStateFromMemento(handler.get(0))
        println("Restored state: ${originator.state}")
    }
}