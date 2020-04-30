/**
 * Iterator pattern manages access to its elements without exposing those inner structure, more over, the whole list
 * required to be iterated in different ways.
 * */
interface Iterator {
    fun hasNext(): Boolean
    fun next(): Any?
}

interface Container {
    fun iterator(): Iterator
}

class NameIterator<T : Any>(private val array: Array<T>) : Iterator {

    private var index: Int = 0

    override fun hasNext(): Boolean = index < array.size

    override fun next(): Any? = if (this.hasNext()) array[index++] else null
}

class NameRepository<T : Any>(private val array: Array<T>) : Container {
    override fun iterator(): Iterator = NameIterator(array)
}

class Test {
    @Test
    fun test() {
        val names: Array<String> = arrayOf("One", "Two", "Three", "Four", "Five")

        val nameRepository = NameRepository(array = names)

        val iterator = nameRepository.iterator()

        while (iterator.hasNext()) {
            val name = iterator.next() as String
            println("Name: $name")
        }
    }
}