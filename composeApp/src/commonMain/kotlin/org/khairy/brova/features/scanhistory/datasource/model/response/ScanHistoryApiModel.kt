import kotlinx.serialization.Serializable

@Serializable
data class ScanHistoryApiModel(
    val success: Boolean,
    val message: String,
    val data: List<Data>?
) {
    @Serializable
    data class Data(
        val id: Int,
        val title: String,
        val image: String,
        val type: String,
        val price: String,
        val currency: String,
        val shop_name: String,
        val description: String,
        val size: String,
        val tshirt_length: Double?,
        val tshirt_width: Double?,
        val pants_length: Double?,
        val pants_waist: Double?,
        val Pants_thigh: Double?
    )
} 