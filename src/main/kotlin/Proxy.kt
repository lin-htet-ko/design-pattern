interface HttpService {
    fun get(path: String)
    fun post(path: String)
}

class HttpServiceImpl: HttpService {

    /*
    private val networkRequestService by lazy { NetworkRequestService() }
     */

    override fun get(path: String) {
        println("https(GET) was requested to $path")
    }

    override fun post(path: String) {
        println("https(POST) was requested to $path")
    }
}

class HttpServiceProxy(private val service: HttpService): HttpService {

    override fun get(path: String) {
        // Do other staff
        service.get(path)
    }

    override fun post(path: String) {
        // Do other staff
        service.post(path)
    }

}

fun main() {
    val service = HttpServiceImpl()
    val proxy = HttpServiceProxy(service = service)

    proxy.get("/images")
    proxy.post("/users?name=Lin")
}