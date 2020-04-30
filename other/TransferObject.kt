/**
 * Transfer Object pattern is applied when we need to assign multiple values inside an object at once from client
 * to server.
 * */
sealed class Model

data class Person(
    var id: Int = 0,
    var name: String = "UNDEFINED"
) : Model()

interface BusinessObject<T : Model> {
    fun all(): List<T>
    fun find(id: Int): T?
    fun create(model: T): Unit
    fun update(model: T?): Unit
    fun delete(model: T): Unit
}

class PersonBusinessObject(private var persons: List<Person> = emptyList()) : BusinessObject<Person> {
    init {
        if (persons.isEmpty()) {
            persons = listOf(
                Person(1, "Albert"),
                Person(2, "Bob"),
                Person(3, "Clark"),
                Person(4, "David"),
                Person(5, "Evan"),
                Person(6, "Frank"),
                Person(7, "George")
            )
        }
    }

    override fun all(): List<Person> = persons

    override fun find(id: Int): Person? = persons.find { it.id == id }

    override fun create(model: Person): Unit {
        persons = persons + model
    }

    override fun update(model: Person?): Unit {
        val person = persons.first { it.id == model?.id }.apply {
            name = model!!.name
        }
        println("UPDATE: first result $person")

        delete(person)

        print("DELETE: result $persons")

        persons = persons + person
    }

    override fun delete(model: Person): Unit {
        persons = persons - model
    }
}

class Test {
    @Test
    fun test(): Unit {
        val businessObject: BusinessObject<Person> = PersonBusinessObject()

        println("Initial persons:")
        businessObject.all().stream().forEach(System.out::println)

        val person = businessObject.find(3)
        println("Found person with id=3: $person")

        businessObject.update(person?.apply {
            name = "Collin"
        })

        println("After modification:")
        businessObject.all().forEach(System.out::println)
    }
}