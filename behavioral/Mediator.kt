/**
 * Mediator is used when need to reduce complexities while communicating with multiple objects or classes.
 * */
data class User(
    var id: Int = Random().nextInt(100),
    var name: String
) {
    fun sendMessage(message: String): Unit = ChatRoom.showMessage(this, message)
}

object ChatRoom {
    fun showMessage(user: User, message: String): Unit = println("${Date()} [from ${user.name}]: $message")
}

class Test {
    @Test
    fun test() {
        val john = User(name = "John")
        val robert = User(name = "Robert")

        john.sendMessage("Hello, Robert!")
        robert.sendMessage("Hello, John!")

    }
}