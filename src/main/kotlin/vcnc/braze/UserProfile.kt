package vcnc.braze

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserProfile(
    val external_id: String,
    val custom_attributes: UserAttr,
    val custom_events: List<UserEvent> = emptyList()
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserEvent(
    val name: String,
    val first: String,
    val last: String,
    val count: Int
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserAttr(
    val signed_up_at: String? = null,

    val user_name: String? = null,

    val is_marketing_notification_enabled: Boolean? = null,

    val is_marketing_email_enabled: Boolean? = null,

    val is_marketing_sms_enabled: Boolean? = null,

    val is_night_marketing_enabled: Boolean? = null,

    val first_marketing_enabled_at: String? = null,

    val is_informative_notification_enabled: Boolean? = null,

    val is_informative_email_enabled: Boolean? = null,

    val is_informative_sms_enabled: Boolean? = null,

    val last_login_at: String? = null,

    val is_inactivated: Boolean? = null,

    val has_payment_method: Boolean? = null,

    val card_publishers: List<String>? = null,

    val has_corporate_payment_method: Boolean? = null,

    val plus_dropped_off_count: Int? = null,

    val lite_dropped_off_count: Int? = null,

    val next_dropped_off_count: Int? = null,

    val daeri_dropped_off_count: Int? = null,

    val private_7_dropped_off_count: Int? = null,

    val private_3_dropped_off_count: Int? = null,

    val private_van_dropped_off_count: Int? = null,

    val air_7_dropped_off_count: Int? = null,

    val air_3_dropped_off_count: Int? = null,

    val air_van_dropped_off_count: Int? = null,

    val recent_origin_address: String? = null,

    val recent_destination_address: String? = null,

    val plus_tip_amount: Int? = null,

    val daeri_tip_amount: Int? = null,

    val air_tip_amount: Int? = null,

    val private_tip_amount: Int? = null,

    val has_subscription: Boolean? = null,

    val subscription_product_name: String? = null,

    val biz_activated: Boolean? = null,

    val biz_company_id: String? = null,

    val friends_sharing_count: Int? = null,

    val friends_sharing_season2_count: Int? = null,

    val is_first_vehicle_registered: Boolean? = null,

    val handlemoa_account_activated: Boolean? = null,

    val handlemoa_daeri_activated: Boolean? = null,

    val driver_daeri_dropped_off_count: Int? = null,

    val handlemoa_daeri_level: Int? = null,

    val driver_lite_signed_up_at: String? = null,

    val driver_lite_dropped_off_count: Int? = null,

    val driver_plus_dropped_off_count: Int? = null,

    val driver_next_dropped_off_count: Int? = null,

    val passport_status: String? = null,

    val is_connected_to_socar: Boolean? = null,

    val passport_purchased_app: String? = null, // 'TADA' / 'SOCAR'가 가능하다.

    val passport_benefit_package: String? = null, // 'TADA' / 'SOCAR'가 가능하다.

    val passport_start_at: String? = null,

    val passport_end_at: String? = null,

    val last_passport_paid_at: String? = null,

    val passport_extend_count: Int? = null,

    val credit_amount: Int? = null,

    val is_favorite_place_registered: Boolean? = null
)
