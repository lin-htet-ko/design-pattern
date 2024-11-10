// third-part payment
class CreditCardPayment {
    fun pay(cost: Double) {}
}
class NetPayment {
    fun pay(cost: Double) {}
}

// source code
enum class PaymentMethod {
    NETS,
    CreditCard
}

class Payment(
    private val netPayment: NetPayment,
    private val creditCardPayment: CreditCardPayment
) {

    fun pay(payment: PaymentMethod, cost: Double) {
        when(payment) {
            PaymentMethod.NETS -> payWithNets(cost)
            PaymentMethod.CreditCard -> payWithCreditCard(cost)
        }
    }

    private fun payWithNets(cost: Double) {
        netPayment.pay(cost)
        println("Pay $cost with Nets payment")
    }

    private fun payWithCreditCard(cost: Double) {
        creditCardPayment.pay(cost)
        println("Pay $cost with CreditCard payment")
    }
}

fun main() {
    val payment = Payment(netPayment = NetPayment(), creditCardPayment = CreditCardPayment())
    payment.pay(PaymentMethod.NETS, 10.1)
}