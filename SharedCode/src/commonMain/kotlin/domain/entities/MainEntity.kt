package domain.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class MainEntity(val id: String,
 val title: String,
 val price: String,
 val price_currency: String,
 val image_url: String)