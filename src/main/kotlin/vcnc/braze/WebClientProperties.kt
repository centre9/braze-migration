package vcnc.braze

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.http.client.reactive.ClientHttpConnector
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.http.client.reactive.ReactorResourceFactory
import reactor.netty.http.HttpResources
import reactor.netty.resources.ConnectionProvider
import java.time.Duration
import java.util.concurrent.TimeUnit

class WebClientProperties {
    var connectTimeout = Duration.ofSeconds(10)!!
    var requestTimeout = Duration.ofSeconds(10)!!
    var maxConnections: Int = 10000
    var poolIdleTimeout = Duration.ofMinutes(1)!!

    fun createClientConnector(poolName: String, followRedirect: Boolean = false): ClientHttpConnector {
        // 참고: https://docs.spring.io/spring-framework/docs/5.2.6.RELEASE/spring-framework-reference/web-reactive.html#webflux-client-builder-reactor
        val resourceFactory = ReactorResourceFactory().apply {
            isUseGlobalResources = false
            connectionProvider = ConnectionProvider.builder(poolName)
                .maxConnections(maxConnections)
                .pendingAcquireMaxCount(-1) // keep the backwards compatibility
                .maxIdleTime(poolIdleTimeout)
                .build()
            loopResources = HttpResources.get()
        }
        return ReactorClientHttpConnector(resourceFactory) { options ->
            options.compress(true)
            options.tcpConfiguration { client ->
                // https://docs.spring.io/spring-framework/docs/5.2.6.RELEASE/spring-framework-reference/web-reactive.html#webflux-client-builder-reactor-timeout
                client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeout.toMillis().toInt())
                    .doOnConnected { conn ->
                        conn.addHandlerLast(ReadTimeoutHandler(requestTimeout.toMillis(), TimeUnit.MILLISECONDS))
                            .addHandlerLast(WriteTimeoutHandler(requestTimeout.toMillis(), TimeUnit.MILLISECONDS))
                    }
            }.let {
                if (followRedirect) {
                    it.followRedirect(true)
                } else {
                    it
                }
            }
        }
    }
}
