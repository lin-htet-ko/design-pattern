import kotlin.concurrent.thread

class Setting private constructor() {

    companion object {
        private lateinit var instance: Setting

        fun setting(): Setting {
            synchronized(this) {
                if (!::instance.isInitialized)
                    instance = Setting()
            }
            return instance
        }
    }

    private var _language: String = "english"

    fun setup(language: String) {
        _language = language
    }
}

fun main() {

    thread {
        println("Thread1: ${Setting.setting()}")
    }
    thread {
        println("Thread2: ${Setting.setting()}")
    }
    thread {
        println("Thread3: ${Setting.setting()}")
    }

}