package com.example.simpleviralgames.model

data class Data(
    val category: List<String>,
    val created_at: String,
    val is_active: Boolean,
    val load_time: Double,
    val multiplayer: Boolean,
    val multiplayer_config: MultiplayerConfig,
    val name: String,
    val offline_support: Boolean,
    val orientation: String,
    val platform: List<String>,
    val `property`: Property,
    val publication: String,
    val rank: Int,
    val slug: String,
    val updated_at: String,
    val url: String,
    val uuid: String
)