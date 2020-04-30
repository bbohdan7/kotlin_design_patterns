/**
 * Intercepting Filter pattern is used to do some pre-processing with request or response like middleware.
 * */
interface Filter {
    fun execute(request: String): Unit
}

class AuthenticationFilter : Filter {
    override fun execute(request: String) = println("Authentication request $request")
}

class DebugFilter : Filter {
    override fun execute(request: String) = println("Request log: $request")
}

class Target {
    fun execute(request: String): Unit = println("Executing request: $request")
}

class FilterChain {
    private var filters: List<Filter> = emptyList()
    private lateinit var target: Target

    fun addFilter(filter: Filter): Unit {
        filters = filters + filter
    }

    fun execute(request: String): Unit {
        filters.forEach { it.execute(request) }
        target.execute(request)
    }

    fun setTarget(target: Target): Unit {
        this.target = target
    }
}

class FilterManager(private val target: Target) {
    private val chain = FilterChain()

    init {
        chain.setTarget(target)
    }

    fun setFilter(filter: Filter): Unit = chain.addFilter(filter)

    fun filterRequest(request: String): Unit = chain.execute(request)
}

class Client {
    private lateinit var filterManager: FilterManager

    fun setFilterManager(filterManager: FilterManager): Unit {
        this.filterManager = filterManager
    }

    fun sendRequest(request: String) = filterManager.filterRequest(request)
}

class Test {
    @Test
    fun test() {
        val filterManager = FilterManager(Target())
        filterManager.setFilter(AuthenticationFilter())
        filterManager.setFilter(DebugFilter())

        val client = Client()
        client.setFilterManager(filterManager)
        client.sendRequest("home")
    }
}