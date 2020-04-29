/**
 * Criteria Pattern (Filter) enables to filter a set of objects by different criteria and chaining them.
 * */

enum class Gender {
    MALE, FEMALE
}

enum class MaritalStatus {
    SINGLE, MARRIED, DIVORCED
}

data class Person(
    val name: String,
    val gender: Gender,
    val status: MaritalStatus
)

interface Criteria {
    fun criteria(persons: List<Person>): List<Person>
}

class Male : Criteria {
    override fun criteria(persons: List<Person>): List<Person> =
        persons.stream().filter { it.gender == Gender.MALE }.collect(
            Collectors.toList()
        )
}

class Female : Criteria {
    override fun criteria(persons: List<Person>): List<Person> =
        persons.stream().filter { it.gender == Gender.FEMALE }.collect(Collectors.toList())
}

class Single : Criteria {
    override fun criteria(persons: List<Person>): List<Person> =
        persons.stream().filter { it.status == MaritalStatus.SINGLE }.collect(Collectors.toList())
}

class Married : Criteria {
    override fun criteria(persons: List<Person>): List<Person> =
        persons.stream().filter { it.status == MaritalStatus.MARRIED }.collect(Collectors.toList())
}

class AndCriteria(private val firstCriteria: Criteria, private val secondCriteria: Criteria) : Criteria {
    override fun criteria(persons: List<Person>): List<Person> =
        secondCriteria.criteria(firstCriteria.criteria(persons))
}

class OrCriteria(private val firstCriteria: Criteria, private val secondCriteria: Criteria) : Criteria {
    override fun criteria(persons: List<Person>): List<Person> {
        var first = firstCriteria.criteria(persons)
        val second = secondCriteria.criteria(persons)

        for (p in second) {
            if (!first.contains(p)) {
                first = first + p
            }
        }

        return first
    }
}

class Test {
    companion object {
        fun printPersons(persons: List<Person>): Unit = persons.forEach(System.out::println)
    }

    @Test
    fun test(): Unit {
        val persons = listOf<Person>(
            Person(name = "Anthony Roberts", gender = Gender.MALE, status = MaritalStatus.SINGLE),
            Person(name = "Julia Johnson", gender = Gender.FEMALE, status = MaritalStatus.SINGLE),
            Person(name = "Samantha Barley", gender = Gender.FEMALE, status = MaritalStatus.MARRIED),
            Person(name = "Paul Richardson", gender = Gender.MALE, status = MaritalStatus.SINGLE),
            Person(name = "Maria Williams", gender = Gender.FEMALE, status = MaritalStatus.DIVORCED),
            Person(name = "Richard Marinas", gender = Gender.MALE, status = MaritalStatus.MARRIED),
            Person(name = "Bob Watson", gender = Gender.MALE, status = MaritalStatus.DIVORCED),
            Person(name = "Rebecca Wallet", gender = Gender.MALE, status = MaritalStatus.MARRIED)
        )

        println("Males:")
        printPersons(Male().criteria(persons))

        println("Females:")
        printPersons(Female().criteria(persons))

        println("Single males:")
        printPersons(AndCriteria(Male(), Single()).criteria(persons))

        println("Single or Females:")
        printPersons(OrCriteria(Single(), Female()).criteria(persons))
    }
}