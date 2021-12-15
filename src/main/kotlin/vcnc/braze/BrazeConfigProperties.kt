package vcnc.braze

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class BrazeConfigProperties (
    @Value("\${transfer-rate}")val transferRate: Int,
    @Value("\${api-key}")val apiKey: String,
    @Value("\${base-url}")val baseUrl: String,
    @Value("\${data-dir}")val dataDir: String,
    @Value("\${list-file-path}")val listFilePath: String,
    @Value("\${upload-attrs}")val uploadAttrs: Boolean,
    @Value("\${upload-events}")val uploadEvents: Boolean
    ) {
    var webClient = WebClientProperties()
}

