abstract class HttpProtocol {
    var httpVersion: String = "HTTP/1.1"
    var body: String? = null
}

class HttpRequestProtocol: HttpProtocol() {
    var method: String = "GET"
    var host: String? = null
    var userAgent: String = "Mozilla/5.0"
    var accept: MutableList<String> = mutableListOf(
        "text/html",
        "application/xhtml+xml",
        "application/xml;q=0.9",
        "image/avif",
        "image/webp",
        "*/*;q=0.8"
    )
    var acceptLanguage: MutableList<String> = mutableListOf("en-GB", "en;q=0.5")
    var acceptAcceptEncoding: MutableList<String> = mutableListOf("gzip", "deflate", "br")
}

class HttpResponseProtocol: HttpProtocol() {
    var code: Int = -1
    var status: String = "OK"
    var contentLength: Double = 0.0
    var lasModified: String? = null
    var server: String? = null
    var eTag: String? = null
    var acceptRange: String? = null
}

abstract class HttpProtocolBuilder<T: HttpProtocol> {
    protected abstract val instance: T
    abstract fun create(): T
}

abstract class HttpRequestBuilder: HttpProtocolBuilder<HttpRequestProtocol>() {
    abstract fun addHttpVersion(version: String): HttpRequestBuilder
    abstract fun addBody(body: String): HttpRequestBuilder
    abstract fun addMethod(method: String): HttpRequestBuilder
    abstract fun addHost(host: String): HttpRequestBuilder
    abstract fun addUserAgent(agent: String): HttpRequestBuilder
    abstract fun addAcceptContent(type: String): HttpRequestBuilder
    abstract fun changeAcceptContent(types: List<String>): HttpRequestBuilder
    abstract fun addAcceptLanguage(lang: String): HttpRequestBuilder
    abstract fun changeAcceptLanguage(languages: List<String>): HttpRequestBuilder
    abstract fun addAcceptEncoding(type: String): HttpRequestBuilder
    abstract fun changeAcceptEncoding(types: List<String>): HttpRequestBuilder
}

class HttpRequestBuilderImpl: HttpRequestBuilder() {

    override var instance: HttpRequestProtocol = HttpRequestProtocol()
    override fun create(): HttpRequestProtocol = instance

    override fun addHttpVersion(version: String): HttpRequestBuilder {
        instance.httpVersion = version
        return this
    }

    override fun addBody(body: String): HttpRequestBuilder {
        instance.body = body
        return this
    }

    override fun addMethod(method: String): HttpRequestBuilder {
        instance.method = method
        return this
    }

    override fun addHost(host: String): HttpRequestBuilder {
        instance.host = host
        return this
    }

    override fun addUserAgent(agent: String): HttpRequestBuilder {
        instance.userAgent = agent
        return this
    }

    override fun addAcceptContent(type: String): HttpRequestBuilder {
        instance.accept.add(type)
        return this
    }

    override fun changeAcceptContent(types: List<String>): HttpRequestBuilder {
        instance.accept = types.toMutableList()
        return this
    }

    override fun addAcceptLanguage(lang: String): HttpRequestBuilder {
        instance.acceptLanguage.add(lang)
        return this
    }

    override fun changeAcceptLanguage(languages: List<String>): HttpRequestBuilder {
        instance.acceptLanguage = languages.toMutableList()
        return this
    }

    override fun addAcceptEncoding(type: String): HttpRequestBuilder {
        instance.acceptAcceptEncoding.add(type)
        return this
    }

    override fun changeAcceptEncoding(types: List<String>): HttpRequestBuilder {
        instance.acceptAcceptEncoding = types.toMutableList()
        return this
    }


}

abstract class HttpResponseBuilder: HttpProtocolBuilder<HttpResponseProtocol>() {
    abstract fun addHttpVersion(version: String): HttpResponseBuilder
    abstract fun addBody(body: String): HttpResponseBuilder
    abstract fun addCode(code: Int): HttpResponseBuilder
    abstract fun addStatus(status: String): HttpResponseBuilder
    abstract fun addContentLength(length: Double): HttpResponseBuilder
    abstract fun addLastModified(modified: String): HttpResponseBuilder
    abstract fun addServer(server: String): HttpResponseBuilder
    abstract fun addETag(tag: String): HttpResponseBuilder
    abstract fun addAcceptRange(range: String): HttpResponseBuilder
}

class HttpResponseBuilderImpl: HttpResponseBuilder() {

    override val instance: HttpResponseProtocol = HttpResponseProtocol()
    
    override fun addHttpVersion(version: String): HttpResponseBuilder {
        instance.httpVersion = version
        return this
    }

    override fun addBody(body: String): HttpResponseBuilder {
        instance.body = body
        return this
    }

    override fun addCode(code: Int): HttpResponseBuilder {
        instance.code = code
        return this
    }

    override fun addStatus(status: String): HttpResponseBuilder {
        instance.status = status
        return this
    }

    override fun addContentLength(length: Double): HttpResponseBuilder {
        instance.contentLength = length
        return this
    }

    override fun addLastModified(modified: String): HttpResponseBuilder {
        instance.lasModified = modified
        return this
    }

    override fun addServer(server: String): HttpResponseBuilder {
        instance.server = server
        return this
    }

    override fun addETag(tag: String): HttpResponseBuilder {
        instance.eTag = tag
        return this
    }

    override fun addAcceptRange(range: String): HttpResponseBuilder {
        instance.acceptRange = range
        return this
    }

    override fun create(): HttpResponseProtocol {
        return instance
    }
}

fun main() {
    val requestBuilder: HttpRequestBuilder = HttpRequestBuilderImpl()
        .addHost("https://www.google.com")
        .addMethod("GET")
    val request: HttpRequestProtocol = requestBuilder.create()
    println(request.host)

    val responseBuilder: HttpResponseBuilder = HttpResponseBuilderImpl()
        .addCode(200)
        .addStatus("OK")
    val response: HttpResponseProtocol = responseBuilder.create()
    println(response.status)
}