package vcnc.braze

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.node.ObjectNode

class TrackUserParam {
}

interface TrackParams {
    val order: Int // 기본값 : 10
}

// https://www.braze.com/docs/developer_guide/rest_api/user_data/#user-attributes-object-specification
// null을 넣으면 값이 삭제되어버림. 원하는 것은 변경된 속성만 merge되는 것이므로 아래 어노테이션을 붙인다.
@JsonInclude(JsonInclude.Include.NON_NULL)
data class TrackUserAttributeParams(
    @JsonIgnore
    override val order: Int = 10,

    // One of "external_id" or "user_alias" or "braze_id" is required
    val external_id: String,

    // Custom Attributes
    @get:JsonProperty("signed_up_at")
    val signed_up_at: String? = null,

    @get:JsonProperty("user_name")
    val user_name: String? = null,

    @get:JsonProperty("is_marketing_notification_enabled")
    val is_marketing_notification_enabled: Boolean? = null,

    @get:JsonProperty("is_marketing_email_enabled")
    val is_marketing_email_enabled: Boolean? = null,

    @get:JsonProperty("is_marketing_sms_enabled")
    val is_marketing_sms_enabled: Boolean? = null,

    @get:JsonProperty("is_night_marketing_enabled")
    val is_night_marketing_enabled: Boolean? = null,

    @get:JsonProperty("first_marketing_enabled_at")
    val first_marketing_enabled_at: String? = null,

    @get:JsonProperty("is_informative_notification_enabled")
    val is_informative_notification_enabled: Boolean? = null,

    @get:JsonProperty("is_informative_email_enabled")
    val is_informative_email_enabled: Boolean? = null,

    @get:JsonProperty("is_informative_sms_enabled")
    val is_informative_sms_enabled: Boolean? = null,

    @get:JsonProperty("last_login_at")
    val last_login_at: String? = null,

    @get:JsonProperty("is_inactivated")
    val is_inactivated: Boolean? = null,

    @get:JsonProperty("has_payment_method")
    val has_payment_method: Boolean? = null,

    val card_publishers: List<String>? = null,

    @get:JsonProperty("has_corporate_payment_method")
    val has_corporate_payment_method: Boolean? = null,

    @get:JsonProperty("plus_dropped_off_count")
    val plus_dropped_off_count: Int? = null,

    @get:JsonProperty("lite_dropped_off_count")
    val lite_dropped_off_count: Int? = null,

    @get:JsonProperty("next_dropped_off_count")
    val next_dropped_off_count: Int? = null,

    @get:JsonProperty("daeri_dropped_off_count")
    val daeri_dropped_off_count: Int? = null,

    @get:JsonProperty("private_7_dropped_off_count")
    val private_7_dropped_off_count: Int? = null,

    @get:JsonProperty("private_3_dropped_off_count")
    val private_3_dropped_off_count: Int? = null,

    @get:JsonProperty("private_van_dropped_off_count")
    val private_van_dropped_off_count: Int? = null,

    @get:JsonProperty("air_7_dropped_off_count")
    val air_7_dropped_off_count: Int? = null,

    @get:JsonProperty("air_3_dropped_off_count")
    val air_3_dropped_off_count: Int? = null,

    @get:JsonProperty("air_van_dropped_off_count")
    val air_van_dropped_off_count: Int? = null,

    @get:JsonProperty("recent_origin_address")
    val recent_origin_address: String? = null,

    @get:JsonProperty("recent_destination_address")
    val recent_destination_address: String? = null,

    @get:JsonProperty("plus_tip_amount")
    val plus_tip_amount: Int? = null,

    @get:JsonProperty("daeri_tip_amount")
    val daeri_tip_amount: Int? = null,

    @get:JsonProperty("air_tip_amount")
    val air_tip_amount: Int? = null,

    @get:JsonProperty("private_tip_amount")
    val private_tip_amount: Int? = null,

    @get:JsonProperty("has_subscription")
    val has_subscription: Boolean? = null,

    @get:JsonProperty("subscription_product_name")
    val subscription_product_name: String? = null,

    @get:JsonProperty("biz_activated")
    val biz_activated: Boolean? = null,

    @get:JsonProperty("biz_company_id")
    val biz_company_id: String? = null,

    @get:JsonProperty("friends_sharing_count")
    val friends_sharing_count: Int? = null,

    @get:JsonProperty("friends_sharing_season2_count")
    val friends_sharing_season2_count: Int? = null,

    @get:JsonProperty("is_first_vehicle_registered")
    val is_first_vehicle_registered: Boolean? = null,

    @get:JsonProperty("handlemoa_account_activated")
    val handlemoa_account_activated: Boolean? = null,

    @get:JsonProperty("handlemoa_daeri_activated")
    val handlemoa_daeri_activated: Boolean? = null,

    @get:JsonProperty("driver_daeri_dropped_off_count")
    val driver_daeri_dropped_off_count: Int? = null,

    @get:JsonProperty("handlemoa_daeri_level")
    val handlemoa_daeri_level: Int? = null,

    @get:JsonProperty("driver_lite_signed_up_at")
    val driver_lite_signed_up_at: String? = null,

    @get:JsonProperty("driver_lite_dropped_off_count")
    val driver_lite_dropped_off_count: Int? = null,

    @get:JsonProperty("driver_plus_dropped_off_count")
    val driver_plus_dropped_off_count: Int? = null,

    @get:JsonProperty("driver_next_dropped_off_count")
    val driver_next_dropped_off_count: Int? = null,

    @get:JsonProperty("passport_status")
    val passport_status: String? = null,

    @get:JsonProperty("is_connected_to_socar")
    val is_connected_to_socar: Boolean? = null,

    @get:JsonProperty("passport_purchased_app")
    val passport_purchased_app: String? = null, // 'TADA' / 'SOCAR'가 가능하다.

    @get:JsonProperty("passport_benefit_package")
    val passport_benefit_package: String? = null, // 'TADA' / 'SOCAR'가 가능하다.

    @get:JsonProperty("passport_start_at")
    val passport_start_at: String? = null,

    @get:JsonProperty("passport_end_at")
    val passport_end_at: String? = null,

    @get:JsonProperty("last_passport_paid_at")
    val last_passport_paid_at: String? = null,

    @get:JsonProperty("passport_extend_count")
    val passport_extend_count: Int? = null,

    @get:JsonProperty("credit_amount")
    val credit_amount: Int? = null,

    @get:JsonProperty("is_favorite_place_registered")
    val is_favorite_place_registered: Boolean? = null
) : TrackParams

// https://www.braze.com/docs/api/endpoints/user_data/#event-object-specification
// null을 넣으면 값이 삭제되어버림. 원하는 것은 변경된 속성만 merge되는 것이므로 아래 어노테이션을 붙인다.
@JsonInclude(JsonInclude.Include.NON_NULL)
data class TrackCustomEventParams(
    @JsonIgnore
    override val order: Int = 10,

    // One of "external_id" or "user_alias" or "braze_id" is required
    val external_id: String,

    // https://www.braze.com/docs/api/api_key/#the-app-identifier-api-key
    val app_id: String? = null,

    val name: String, // the name of the event

    val time: String, // datetime as string in ISO 8601 or in `yyyy-MM-dd'T'HH:mm:ss.SSSZ` format

    val properties: Map<String, Any>? = null
) : TrackParams

// https://www.braze.com/docs/developer_guide/rest_api/user_data/#user-track-request
@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserTrackRequest(
    val api_key: String,
    val attributes: List<ObjectNode>?,
    val events: List<ObjectNode>?
)

// https://www.braze.com/docs/developer_guide/rest_api/user_data/#user-track-responses
@JsonIgnoreProperties(ignoreUnknown = true)
data class UserTrackResponse(
    // "success", "queued", or error message
    val message: String
) {
    fun isSuccessful() = message == "success" || message == "queued"
}

