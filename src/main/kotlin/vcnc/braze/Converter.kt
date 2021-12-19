package vcnc.braze

import org.springframework.stereotype.Component
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.min

@Component
class Converter(
    val properties: BrazeConfigProperties,
    ) {
    fun convertUserEventListTolTrackParamsList(events: List<UserEvent>, id: String): List<TrackParams> {
        return events
            .map { convertUserEventToTrackParamsList(it, id) }
            .flatten()
    }

    private fun convertUserEventToTrackParamsList(event: UserEvent, id: String): List<TrackParams> {
        val first = LocalDateTime.parse(event.first, DateTimeFormatter.ISO_ZONED_DATE_TIME)
        val last = LocalDateTime.parse(event.last, DateTimeFormatter.ISO_ZONED_DATE_TIME)
        val count = min(properties.maxObjectCount, event.count)

        val result = (0 until count-1).map {
            TrackCustomEventParams(
                external_id = id,
                name = event.name,
                time = (first + Duration.ofMillis(10L * it)).toString(),
            )
        }

        return result + listOf(
            TrackCustomEventParams(
                external_id = id,
                name = event.name,
                time = last.toString(),
            )
        )
    }

    fun convertUserAttrToTrackParams(attr: UserAttr, id: String): TrackParams {
        return with(attr) {
            TrackUserAttributeParams(
                external_id = id,
                signed_up_at = signed_up_at,
                user_name = user_name,
                is_marketing_notification_enabled = is_marketing_notification_enabled,
                is_marketing_email_enabled = is_marketing_email_enabled,
                is_marketing_sms_enabled = is_marketing_sms_enabled,
                is_night_marketing_enabled = is_night_marketing_enabled,
                first_marketing_enabled_at = first_marketing_enabled_at,
                is_informative_notification_enabled = is_informative_notification_enabled,
                is_informative_email_enabled = is_informative_email_enabled,
                is_informative_sms_enabled = is_informative_sms_enabled,
                last_login_at = last_login_at,
                is_inactivated = is_inactivated,
                has_payment_method = has_payment_method,
                has_corporate_payment_method = has_corporate_payment_method,
                plus_dropped_off_count = plus_dropped_off_count,
                lite_dropped_off_count = lite_dropped_off_count,
                next_dropped_off_count = next_dropped_off_count,
                daeri_dropped_off_count = daeri_dropped_off_count,
                private_7_dropped_off_count = private_7_dropped_off_count,
                private_3_dropped_off_count = private_3_dropped_off_count,
                private_van_dropped_off_count = private_van_dropped_off_count,
                air_7_dropped_off_count = air_7_dropped_off_count,
                air_3_dropped_off_count = air_3_dropped_off_count,
                air_van_dropped_off_count = air_van_dropped_off_count,
                recent_origin_address = recent_origin_address,
                recent_destination_address = recent_destination_address,
                plus_tip_amount = plus_tip_amount,
                daeri_tip_amount = daeri_tip_amount,
                air_tip_amount = air_tip_amount,
                private_tip_amount = private_tip_amount,
                has_subscription = has_subscription,
                subscription_product_name = subscription_product_name,
                biz_activated = biz_activated,
                biz_company_id = biz_company_id,
                friends_sharing_count = friends_sharing_count,
                friends_sharing_season2_count = friends_sharing_count,
                is_first_vehicle_registered = is_first_vehicle_registered,
                handlemoa_account_activated = handlemoa_account_activated,
                handlemoa_daeri_activated = handlemoa_daeri_activated,
                driver_daeri_dropped_off_count = driver_daeri_dropped_off_count,
                handlemoa_daeri_level = handlemoa_daeri_level,
                driver_lite_signed_up_at = driver_lite_signed_up_at,
                driver_lite_dropped_off_count = driver_lite_dropped_off_count,
                driver_plus_dropped_off_count = driver_plus_dropped_off_count,
                driver_next_dropped_off_count = driver_next_dropped_off_count,
                passport_status = passport_status,
                is_connected_to_socar = is_connected_to_socar,
                passport_purchased_app = passport_purchased_app,
                passport_benefit_package = passport_status,
                passport_start_at = passport_start_at,
                passport_end_at = passport_end_at,
                last_passport_paid_at = last_passport_paid_at,
                passport_extend_count = passport_extend_count,
                credit_amount = credit_amount,
                is_favorite_place_registered = is_favorite_place_registered
            )
        }
    }
}