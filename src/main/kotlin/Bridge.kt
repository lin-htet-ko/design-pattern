interface AnalyticService {
    fun send(log: String)
}

class DataDogAnalyticService: AnalyticService {
    override fun send(log: String) {
        println("Sent to data-dog server: $log")
    }
}

class FirebaseAnalyticService: AnalyticService {
    override fun send(log: String) {
        println("Sent to firebase server: $log")
    }
}

class AnalyticTracker(
    private var service: AnalyticService,
) {
    fun changeService(service: AnalyticService) {
        this.service = service
    }

    fun sendLog(message: String) {
        service.send(message)
    }
}

fun main() {
    val firebase = FirebaseAnalyticService()
    val dataDog = DataDogAnalyticService()

    val tracker = AnalyticTracker(firebase)
    tracker.sendLog("User click setting button")

    tracker.changeService(dataDog)
    tracker.sendLog("User click setting button")
}