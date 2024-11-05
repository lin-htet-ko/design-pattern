enum class OSType {
    Android,
    IOS
}

abstract class Button {
    abstract fun addOutline(width: Float, color: Long, radius: Float)
    abstract fun addBackground(color: Long)
}

class AndroidButton: Button() {
    override fun addOutline(width: Float, color: Long, radius: Float) {
        println("Added Android Button Outline: width: $width, color: $color, radius: $radius")
    }

    override fun addBackground(color: Long) {
        println("Added Android Button Background: color: $color")
    }
}

class IOSButton: Button() {
    override fun addOutline(width: Float, color: Long, radius: Float) {
        println("Added IOS Button Outline: width: $width, color: $color, radius: $radius")
    }

    override fun addBackground(color: Long) {
        println("Added IOS Button Background: color: $color")
    }
}

abstract class UiComponent(protected val type: OSType) {
    abstract fun createButton(): Button
}

object AndroidUiComponent: UiComponent(OSType.Android) {
    override fun createButton() = AndroidButton()
}

object IOSUiComponent: UiComponent(OSType.IOS) {
    override fun createButton() = IOSButton()
}

class UiComponentFactory(private val type: OSType) {
    fun component(): UiComponent = when(type) {
        OSType.Android -> AndroidUiComponent
        OSType.IOS -> IOSUiComponent
    }
}

fun main() {

    val android = UiComponentFactory(OSType.Android)
    android.component().createButton().apply {
        addBackground(0xFFFFFFFF)
        addOutline(1f, 0xFF000000, 0.5f)
    }

    val ios = UiComponentFactory(OSType.IOS)
    ios.component().createButton().apply {
        addBackground(0xFF000000)
        addOutline(1f, 0xFFFFFFFF, 0.5f)
    }
}