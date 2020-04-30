/**
 * Business Delegate Pattern decouples presentation tier and business tier to reduce communication between them.
 * 1) Client - any view (HTML, JSF, JSP etc)
 * 2) Business Delegate - enitry point class for clients which provides business logic
 * 3) Lookup service - an object responsible for business implementation and provide business object access
 * to delegate object.
 * 4) Business service - business service interface. Concrete classes implement this business service.
 * */

interface BusinessService {
    fun process(): Unit
}

class EJBService : BusinessService {
    override fun process() = println("Processing EJB service.")
}

class JMSService : BusinessService {
    override fun process() = println("Processing JSM service")
}

object BusinessLookup {
    enum class ServiceType {
        EJB, JMS
    }

    fun getBusinessService(serviceType: ServiceType): BusinessService = when (serviceType) {
        ServiceType.EJB -> EJBService()
        ServiceType.JMS -> JMSService()
    }
}

class BusinessDelegate {
    private lateinit var serviceType: BusinessLookup.ServiceType

    fun setServiceType(serviceType: BusinessLookup.ServiceType) {
        this.serviceType = serviceType
    }

    fun task() = BusinessLookup.getBusinessService(serviceType).process()
}

class Client(private val service: BusinessDelegate) {
    fun task() = service.task()
}

class Test {
    @Test
    fun test() {
        val delegate = BusinessDelegate()
        delegate.setServiceType(BusinessLookup.ServiceType.EJB)

        val client = Client(delegate)
        client.task()

        delegate.setServiceType(BusinessLookup.ServiceType.JMS)
        client.task()
    }
}
