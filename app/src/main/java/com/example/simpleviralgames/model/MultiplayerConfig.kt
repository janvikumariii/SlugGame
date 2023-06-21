package com.example.simpleviralgames.model

data class MultiplayerConfig(
    val max_players: Int,
    val min_players: Int,
    val supported_modes: List<String>
)