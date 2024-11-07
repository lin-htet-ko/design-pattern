data class Passenger(val id: Long, val name: String, val tripId: Long)

object DataSource {
    val passengers = mutableListOf<Passenger>()
}

interface PassengerService {
    fun addPassenger(passenger: Passenger)
}

// third party sdk class
class RecordAnalytics {
    fun record(data: String) {
        println("Record: $data")
    }
}

class PassengerAdapter(private val source: DataSource, private val analytics: RecordAnalytics) : PassengerService {
    override fun addPassenger(passenger: Passenger) {
        source.passengers.add(passenger)
        analytics.record(passenger.toString())
    }

}

fun main() {
    val source = DataSource

    val passenger = Passenger(id = 1, name = "Lin Htet Ko", tripId = 139)

    val analytics = RecordAnalytics()
    val adapter = PassengerAdapter(source = source, analytics = analytics)
    adapter.addPassenger(passenger)

    println(source.passengers.toString())
}