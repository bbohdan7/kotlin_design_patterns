/**
 * Composite pattern is helpful when you need to deal with a group of objects in one defined way as a single object.
 * */
data class Employee(
    var name: String,
    var dept: String,
    var salary: Int,
    var subordinates: List<Employee> = emptyList()
)

class Test {
    @Test
    fun test() {
        val cto = Employee(name = "John Johnson", dept = "CTO", salary = 30000) //There is no subordinates

        val dev1 = Employee(name = "Robert Robertson", dept = "IT", salary = 10000)
        val dev2 = Employee(name = "William Williamson", dept = "IT", salary = 10000)

        cto.subordinates += dev1
        cto.subordinates += dev2

        println("CTO $cto")
    }
}