package domain.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class EntityDetails(  val title: String,
                           val description: String,
                           val price: String,
                           val image_url: String)