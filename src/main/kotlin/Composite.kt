data class Placeable(val x: Float, val y: Float)

interface Component {
    fun draw()
    fun place(x: Float, y: Float): Placeable
}

class Image(val url: String) : Component {
    override fun draw() {
//        TODO("Not yet implemented")
    }

    override fun place(x: Float, y: Float): Placeable {
//        TODO("Not yet implemented")
        return Placeable(0f, 0f)
    }
}

class Text(val text: String) : Component {
    override fun draw() {
//        TODO("Not yet implemented")
    }

    override fun place(x: Float, y: Float): Placeable {
//        TODO("Not yet implemented")
        return Placeable(0f, 0f)
    }
}

class Switch(val state: Boolean) : Component {
    override fun draw() {
//        TODO("Not yet implemented")
    }

    override fun place(x: Float, y: Float): Placeable {
//        TODO("Not yet implemented")
        return Placeable(0f, 0f)
    }
}

class Row(private var coordinate: Placeable, components: List<Component>) : Component {

    private val _components = components.toMutableList()

    init {
        components.forEach(::process)
    }

    fun add(component: Component) {
        _components.add(component)
        process(component)
    }

    private fun process(component: Component) {
        val placeable = component.place(coordinate.x, coordinate.y)
        coordinate = Placeable(placeable.x + coordinate.x, placeable.y + coordinate.y)

        component.draw()
    }

    override fun draw() {
        TODO("Not yet implemented")
    }

    override fun place(x: Float, y: Float): Placeable {
//        TODO("Not yet implemented")
        return Placeable(0f, 0f)
    }

}

fun main() {
    Row(
        coordinate = Placeable(0f, 0f),
        components = listOf(
            Text("Text Component"),
            Image("https://example.com/image.png"),
            Switch(true)
        )
    )
}