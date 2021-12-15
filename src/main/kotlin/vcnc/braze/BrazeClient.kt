package vcnc.braze

import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Flux

@Component
class BrazeClient(
    webClientBuilder: WebClient.Builder,
    private val properties: BrazeConfigProperties
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    private val connector = properties.webClient.createClientConnector("brazeClientPool")
    private val webClient = webClientBuilder
        .uriBuilderFactory(LegacyUriBuilderFactory(properties.baseUrl))
        .defaultHeader("Content-Type", "application/json")
        .defaultHeader("Authorization", "Bearer ${properties.apiKey}")
        .clientConnector(connector)
        .build()
    private val mapper = jacksonObjectMapper()

    fun track(trackParamsList: List<TrackParams>) {
        if (trackParamsList.isEmpty()) return
        Flux.concat(
            trackParamsList
                .groupBy { it.order }
                .toList()
                .map { (_, trackParams) ->
                    val request = UserTrackRequest(
                        api_key = properties.apiKey,
                        attributes = trackParams.filterIsInstance<TrackUserAttributeParams>().map { mapper.valueToTree<ObjectNode>(it) },
                        events = trackParams.filterIsInstance<TrackCustomEventParams>().map { mapper.valueToTree<ObjectNode>(it) }
                    )
                    webClient.post()
                        .uri("/users/track")
                        .bodyValue(request)
                        .retrieve()
                        .bodyToMono<UserTrackResponse>()
                }
        )
            .subscribe({
                if (!it.isSuccessful()) {
                    logger.error("[Braze] ${it.message}")
                }
            }) { _ ->
                throw Exception("Error on track")
            }
    }

    fun jsonToProfile(jsonStr: String): UserProfile {
        return  try {
            mapper.readValue(jsonStr)
        } catch (e: Exception) {
            throw e
        }
    }
}