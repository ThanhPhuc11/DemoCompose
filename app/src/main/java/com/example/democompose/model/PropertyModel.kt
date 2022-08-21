package com.example.democompose.model

import com.google.gson.annotations.SerializedName

data class PropertyModel(
    var health: String? = null,
    var mana: String? = null,
    var armor: String? = null,
    var mr: String? = null,
    var damage: String? = null,
    @SerializedName("atk_spd")
    var atkSpd: String? = null,
    var range: Int? = null
)