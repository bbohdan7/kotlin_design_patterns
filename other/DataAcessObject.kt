/**
 * Data Access Object (DAO) pattern applied when necessary to divide low level data API accessing or operations
 * from high level business services.
 */
abstract class Data

data class Person(
    var id: Int = 0,
    var name: String = "UNDEFINED"
) : Data()

interface DataAccessInterface<T : Data> {
    fun all(): List<T>
    fun find(id: Int): T
    fun update(model: T): Unit
    fun delete(model: T): Unit
    fun create(model: T): Unit
}

class PersonDAO(private var persons: List<Person>) : DataAccessInterface<Person> {
    override fun all(): List<Person> = persons

    override fun find(id: Int): Person = persons.first { it.id == id }

    override fun update(model: Person): Unit {
        val person: Person = persons.first { it.id == model.id }.apply {
            name = model.name
        }
        delete(person)
        persons = persons + person
    }

    override fun delete(model: Person) {
        persons = persons - persons.first { it.id == model.id }
    }

    override fun create(model: Person): Unit {
        persons = persons + model
    }
}

class Test {
    @Test
    fun test() {
        val dao = PersonDAO(
            listOf(
                Person(1, "John"),
                Person(2, "Robert"),
                Person(3, "Julia")
            )
        )

        dao.update(Person(2, "Albert"))
        dao.update(Person(3, "Vanessa"))

        dao.create(Person(4, "Joe"))

        println(dao.all())

        dao.delete(Person(4, "Joe"))

        println(dao.all())
    }
}