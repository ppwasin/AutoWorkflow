package com.boot.backend

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TestApplication {
	//    val mockEngine = MockEngine { _ ->
//        respond(
//            content = ByteReadChannel("""{"ip":"127.0.0.1"}"""),
//            status = HttpStatusCode.OK,
//            headers = headersOf(HttpHeaders.ContentType, "application/json")
//        )
//    }
	@BeforeEach
	fun setup() {

	}

	@Test
	fun `first test`() {
//        runBlocking {
//            val mockEngine = MockEngine { request ->
//                respond(
//                    content = ByteReadChannel("""{"ip":"127.0.0.1"}"""),
//                    status = HttpStatusCode.OK,
//                    headers = headersOf(HttpHeaders.ContentType, "application/json")
//                )
//            }
//            val apiClient = ApiClient(mockEngine)
//
//            "127.0.0.1" shouldBe apiClient.getIp().ip
//        }
	}
}
