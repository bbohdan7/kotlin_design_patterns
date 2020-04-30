/**
 * MVC Pattern stands for Model-View-Controller and used to separate concerns inside the system, as a rule we separate
 * logic and presentation, user communicates with Controller, and Model and View responsible for retrieving Data and
 * generating response as a view respectively.
 * This pattern is usually applied as a huge paradigm on which everything is based.
 * */
abstract class Model

abstract class View<T : Model> {
    abstract fun render(model: T): Unit
}

abstract class Controller<T : Model, V : View<T>>(protected var model: T, protected var view: V) {
    abstract fun updateView(): Unit
}

data class Person(
    var id: Int = 0,
    var name: String = "UNDEFINED"
) : Model()

class PersonView : View<Person>() {
    override fun render(model: Person): Unit = println("Person's id=${model.id}, name=${model.name} ")
}

class PersonController(model: Person, view: PersonView) : Controller<Person, View<Person>>(model, view) {

    fun updateName(name: String) {
        super.model.name = name
    }

    fun updateId(id: Int) {
        super.model.id = id
    }

    fun getPersonName() = super.model.name
    fun getPersonId() = super.model.name

    override fun updateView(): Unit = view.render(model)
}

class Test {
    @Test
    fun test() {
        val model = fetchPerson()
        val view = PersonView()

        val controller = PersonController(model, view)
        controller.updateView()

        controller.updateName("John")

        controller.updateView()
    }

    private fun fetchPerson(): Person = Person().apply {
        id = 1
        name = "Robert"
    }
}