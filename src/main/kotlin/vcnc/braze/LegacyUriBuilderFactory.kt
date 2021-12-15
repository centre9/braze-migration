package vcnc.braze

import org.springframework.web.util.DefaultUriBuilderFactory


class LegacyUriBuilderFactory : DefaultUriBuilderFactory {
    constructor() : super()

    constructor(baseUrl: String) : super(baseUrl)

    init {
        encodingMode = EncodingMode.URI_COMPONENT
    }
}
