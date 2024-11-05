abstract class Delivery {

    enum class Type {
        Lazada,
        LalaMove,
        FlashMove
    }

    abstract fun delivery(): String
}

class LazadaDelivery: Delivery() {
    override fun delivery(): String {
        return "Lazada Delivery"
    }
}

class LalaMoveDelivery: Delivery() {
    override fun delivery(): String {
        return "LalaMove Delivery"
    }
}

class FlashMoveDelivery: Delivery() {
    override fun delivery(): String {
        return "FlashMove Delivery"
    }
}

object DeliveryFactory {

    private val deliveries = mutableMapOf<Delivery.Type, Delivery>()

    fun create(type: Delivery.Type): Delivery {
        return deliveries.getOrPut(type) {
            when(type) {
                Delivery.Type.Lazada -> LazadaDelivery()
                Delivery.Type.LalaMove -> LalaMoveDelivery()
                Delivery.Type.FlashMove -> FlashMoveDelivery()
            }
        }
    }

}

fun main() {

    val lazada: Delivery = DeliveryFactory.create(Delivery.Type.Lazada)
    println("${lazada.delivery()} $lazada")
    println("${lazada.delivery()} $lazada")

    val lalaMove: Delivery = DeliveryFactory.create(Delivery.Type.LalaMove)
    println("${lalaMove.delivery()} $lalaMove")
    println("${lalaMove.delivery()} $lalaMove")

    val flashMove: Delivery = DeliveryFactory.create(Delivery.Type.FlashMove)
    println("${flashMove.delivery()} $flashMove")
    println("${flashMove.delivery()} $flashMove")


}