/**
 * Modify objects' behaviour depending on its state.
 * Context class delegates dependent request to the current object CONCRETE_STATE (keeps instance of subclass which
 * defines current state) defines interface needed for the end-users.
 * */
data class Context(var state: State? = null)

interface State {
    fun action(context: Context): Unit
}

class StartState : State {
    override fun action(context: Context): Unit {
        println("Initial state")
        context.state = this
    }

    override fun toString() = "START_STATE"
}

class StopState : State {
    override fun action(context: Context): Unit {
        println("Stop state")
        context.state = this
    }

    override fun toString() = "STOP_STATE"
}

class Test {
    @Test
    fun test() {
        val context = Context()

        val start = StartState()
        start.action(context)

        println(context.state)

        val stop = StopState()
        stop.action(context)

        println(context.state)
    }
}