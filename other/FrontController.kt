/**
 * Front Controller Pattern is an object to handle many objects as a single one. Usually it is used to handle
 * Authentication/Authorization/Logging/Tracking processes to the request.
 * 1) Front Controller - handler for many requests
 * 2) Dispatcher - an object decides which Front Controller should be used
 * 3) View - the final object returned to the user
 * */

interface View {
    fun display(): Unit
}

class Home : View {
    override fun display() = println("home.html")
}

class Contacts : View {
    override fun display() = println("contacts.html")
}

class Dispatcher {
    private val home: View = Home()
    private val contacts: View = Contacts()

    fun dispatch(request: String): Unit {
        if (request == "contacts")
            contacts.display()
        else
            home.display()
    }
}

class FrontController {
    private val dispatcher = Dispatcher()

    fun isAuthenticated(): Boolean {
        println("User is authenticated!")
        return true;
    }

    fun trackRequest(request: String): Unit = println("Requested page: $request")

    fun dispatchRequest(request: String): Unit {
        trackRequest(request)

        if (isAuthenticated()) dispatcher.dispatch(request)
    }
}

class Test {
    @Test
    fun test(){
        val frontController = FrontController()

        frontController.dispatchRequest("home")
        frontController.dispatchRequest("contacts")
        frontController.dispatchRequest("unknown")
    }
}