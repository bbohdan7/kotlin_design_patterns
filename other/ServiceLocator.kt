/**
 * Service Locator pattern may be useful in EJB specific enterprise applications, it enables to locate many services
 * using JNDI lookup.
 * This pattern makes use of caching mechanism.
 * */

interface Service {
    fun getName(): String
    fun execute(): Unit
}

class FirstService : Service {
    override fun getName(): String = "service_1"

    override fun execute(): Unit = println("Executing service FirstService")
}

class SecondService : Service {
    override fun getName(): String = "service_2"

    override fun execute(): Unit = println("Executing service SecondService")
}

class InitialContext {
    inner class UnknownServiceException : Exception {
        constructor() : super()
        constructor(message: String) : super(message)
    }

    fun lookup(jndiName: String): Any = when (jndiName) {
        "service_1" -> {
            println("Lookup for FirstService object")
            FirstService()
        }
        "service_2" -> {
            println("Lookup for SecondService object")
            SecondService()
        }
        else -> throw UnknownServiceException(message = "Unable to lookup service with name $jndiName")
    }
}

class Cache {
    private var services: List<Service> = emptyList()

    fun getService(serviceName: String): Service? {
        val count: Int = services.count { it.getName() == serviceName }

        if (count > 0) {
            println("Cached value for $serviceName has been found.")
            return services.first { it.getName() == serviceName }
        }

        return null
    }

    fun addService(service: Service): Unit {
        val exists: Boolean = services.any {
            it.getName() == service.getName()
        }

        if (!exists) {
            services = services + service
        }
    }
}

object ServiceLocator {
    private var cache = Cache()

    fun getService(jndiName: String): Service {
        val service: Service? = cache.getService(jndiName)

        if (service != null) return service

        val firstService: Service = InitialContext().lookup(jndiName) as Service

        cache.addService(firstService)

        return firstService
    }
}

class Test {
    @Test
    fun test() {
        var service = ServiceLocator.getService("service_1")
        service.execute()

        service = ServiceLocator.getService("service_2")
        service.execute()

        service = ServiceLocator.getService("service_1")
        service.execute()

        service = ServiceLocator.getService("service_2")
        service.execute()
    }
}