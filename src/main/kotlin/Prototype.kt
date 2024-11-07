interface Copyable {
    fun copy(): Copyable
}

class User(private val name: String, private val age: Int) : Copyable {
    override fun copy(): Copyable {
        return User(name = name, age = age)
    }

    override fun toString(): String {
        return "User(name=$name, age=$age)"
    }
}

fun main() {
    val user = User("Lin Htet Ko", 190)
    val user2 = user.copy() as User

    println(user.toString())
    println(user2.toString())
}